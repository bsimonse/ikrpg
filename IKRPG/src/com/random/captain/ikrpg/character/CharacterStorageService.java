package com.random.captain.ikrpg.character;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gag.annotation.disclaimer.ProbablyIllegalIn;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.enumeration.RegionType;
import com.random.captain.ikrpg.IKRPGApp;

public class CharacterStorageService
{
	//IT'S DANGEROUS TO CODE ALONE
	//TAKE THESE
	public interface LoadingDelegate<T extends zzBaseCharacter>
	{public void charactersLoaded(Set<T> characters);}
	
	public interface SavingDelegate
	{public void characterSaved(boolean succeeded);}
	
	public interface DeleteDelegate
	{public void characterDeleted(boolean succeeded);}
	
	
	
	public static Set<String> getSavedCharacterNames()
	{
		Set<String> charNames = new HashSet<String>();
		Context c = IKRPGApp.getContext();
		for(File file : c.getDir("characters",Context.MODE_PRIVATE).listFiles())
		{
			charNames.add(fileNameToCharacterName(file.getName()));
		}
		
		return charNames;
	}
	
	public static <T extends zzBaseCharacter> void loadCharacters(Set<String> characterNames, Class<T> pCharClass, CharacterStorageService.LoadingDelegate<T> pDelegate)
	{
		new LoadCharacterTask<T>(pCharClass, pDelegate).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, characterNames.toArray(new String[0]));
	}
	
	public static <T extends zzBaseCharacter> void saveCharacter(T character, CharacterStorageService.SavingDelegate pDelegate)
	{
		new SaveCharacterTask<T>(pDelegate, character).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
	
	public static void deleteCharacter(String charName, CharacterStorageService.DeleteDelegate pDelegate)
	{
		new DeleteCharacterTask(pDelegate, charName).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
	
	private static String fileNameToCharacterName(String fileName)
	{
		try {
			return URLDecoder.decode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// guess we couldn't do it.
			return fileName;
		}
	}
	
	private static String characterNameToFileName(String characterName)
	{
		try {
			return URLEncoder.encode(characterName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// guess we couldn't do it.
			return characterName;
		}
	}
	
	private static class LoadCharacterTask<T extends zzBaseCharacter> extends AsyncTask<String, Void, TreeSet<T>>
	{
		private CharacterStorageService.LoadingDelegate<T> delegate;
		private Class<T> charClass;
		private TreeSet<T> characters = new TreeSet<T>(new Comparator<T>(){
			@Override
			public int compare(T t1, T t2)
			{
				return t1.fluff.name.compareTo(t2.fluff.name);
			}
		});
		
		public LoadCharacterTask(Class<T> pCharClass, CharacterStorageService.LoadingDelegate<T> pDelegate)
		{
			delegate = pDelegate;
			charClass = pCharClass;
		}
	
		@Hack
		@ProbablyIllegalIn(number = 30, region = RegionType.STATES)
		@Override protected TreeSet<T> doInBackground(String... names)
		{
			for(String name : names)
			{
				BufferedReader reader = null;
				StringBuilder sb = new StringBuilder();
				
				try
				{
					Context c = IKRPGApp.getContext();
					File dir = c.getDir("characters",Context.MODE_PRIVATE);
					File inFile = new File(dir, characterNameToFileName(name));
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
					
					String line;
					while((line = reader.readLine()) != null){sb.append(line);}
		
					Method m = charClass.getMethod("fromJson", String.class);
					T character = charClass.cast(m.invoke(null, sb.toString()));
					characters.add(character);
				}
				catch(Exception e)
				{
					//oops.
					Log.e("IKRPG","Reading characters from stream didn't work. "+e.getMessage());
				}
				finally
				{
					//lol whut
					if(reader != null){try {
						reader.close();
					} catch (IOException e) {
						Log.i("IKRPG","Well, now we're just hosed; character input stream failed to close");
					}}
				}
			}	
	
			return characters;
		}
	
		@Override protected void onPostExecute(TreeSet<T> chars)
		{
			if(delegate != null){delegate.charactersLoaded(chars);}
		}
	}
	
	private static class SaveCharacterTask<T extends zzBaseCharacter> extends AsyncTask<Void, Void, Boolean>
	{
		private CharacterStorageService.SavingDelegate delegate;
		T myChar;
	
		public SaveCharacterTask(CharacterStorageService.SavingDelegate pDelegate, T pChar)
		{
			delegate = pDelegate;
			myChar = pChar;
		}

		@Override protected Boolean doInBackground(Void... unused)
		{			
			try
			{
				Context c = IKRPGApp.getContext();
				File dir = c.getDir("characters",Context.MODE_PRIVATE);
				File outFile = new File(dir, characterNameToFileName(myChar.fluff.name));
				
				String outputJson = myChar.toJson();
				FileOutputStream os = new FileOutputStream(outFile);
				os.write(outputJson.getBytes("UTF-8"));
				
				os.flush();os.close();
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Didn't save!  "+e.getMessage());
				return false;
			}
			
			return true;
		}
	
		@Override protected void onPostExecute(Boolean success)
		{
			if(delegate != null){delegate.characterSaved(success);}
		}
	}
	
	private static class DeleteCharacterTask extends AsyncTask<Void, Void, Boolean>
	{
		private CharacterStorageService.DeleteDelegate delegate;
		String charName;

		public DeleteCharacterTask(CharacterStorageService.DeleteDelegate pDelegate, String pCharName)
		{
			delegate = pDelegate;
			charName = pCharName;
		}

		@Override protected Boolean doInBackground(Void... unused)
		{			
			try
			{
				Context c = IKRPGApp.getContext();
				File dir = c.getDir("characters",Context.MODE_PRIVATE);
				File outFile = new File(dir, characterNameToFileName(charName));

				return outFile.delete();
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Wait, character delete threw exception?  "+e.getMessage());
				return false;
			}
		}

		@Override protected void onPostExecute(Boolean success)
		{
			if(delegate != null){delegate.characterDeleted(success);}
		}
	}
}
