package com.random.captain.ikrpg.character;

import java.io.*;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gag.annotation.disclaimer.ProbablyIllegalIn;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.enumeration.RegionType;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CharacterStorageService
{
	//IT'S DANGEROUS TO CODE ALONE
	//TAKE THESE
	public interface LoadingDelegate<T extends zzBaseCharacter>
	{public void charactersLoaded(Set<T> characters);}
	
	public interface SavingDelegate
	{public void characterSaveComplete(boolean succeeded);}
	
	
	
	
	private Context c;
	
	public CharacterStorageService(Context pContext)
	{
		c = pContext;
	}
	
	public Set<String> getSavedCharacterNames()
	{
		Set<String> charNames = new HashSet<String>();
		for(File file : c.getDir("characters",Context.MODE_PRIVATE).listFiles())
		{
			charNames.add(file.getName());
		}
		
		return charNames;
	}
	
	public <T extends zzBaseCharacter> void loadCharacters(Set<String> characterNames, Class<T> pCharClass, CharacterStorageService.LoadingDelegate<T> pDelegate)
	{
		new LoadCharacterTask<T>(pCharClass, pDelegate).execute(characterNames.toArray(new String[0]));
	}
	
	public <T extends zzBaseCharacter> void saveCharacter(Set<T> characters, CharacterStorageService.SavingDelegate pDelegate)
	{
		new SaveCharacterTask<T>(pDelegate, characters).execute();
	}

	private class LoadCharacterTask<T extends zzBaseCharacter> extends AsyncTask<String, Void, Set<T>>
	{
		private CharacterStorageService.LoadingDelegate<T> delegate;
		private Class<T> charClass;
		private Set<T> characters = new HashSet<T>();
		
		public LoadCharacterTask(Class<T> pCharClass, CharacterStorageService.LoadingDelegate<T> pDelegate)
		{
			delegate = pDelegate;
			charClass = pCharClass;
		}
	
		@Hack
		@ProbablyIllegalIn(number = 30, region = RegionType.STATES)
		@Override protected Set<T> doInBackground(String... names)
		{
			for(String name : names)
			{
				FileInputStream stream = null;
				StringBuilder sb = new StringBuilder();
				
				try
				{
					File dir = c.getDir("characters",Context.MODE_PRIVATE);
					File inFile = new File(dir, name);
					stream = new FileInputStream(inFile);
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
					
					String line;
					while((line = reader.readLine()) != null){sb.append(line);}
		
					stream.close();
					Method m = charClass.getMethod("fromJson", String.class);
					T character = charClass.cast(m.invoke(null, sb.toString()));
					characters.add(character);
				}
				catch(Exception e)
				{
					//oops.
					Log.e("IKRPG","That didn't work. "+e.getMessage());
				}
			}	
	
			return characters;
		}
	
		@Override protected void onPostExecute(Set<T> chars)
		{
			if(delegate != null){delegate.charactersLoaded(chars);}
		}
	}
	
	private class SaveCharacterTask<T extends zzBaseCharacter> extends AsyncTask<Void, Void, Boolean>
	{
		private CharacterStorageService.SavingDelegate delegate;
		Set<T> characters;
	
		public SaveCharacterTask(CharacterStorageService.SavingDelegate pDelegate, Set<T> pCharacters)
		{
			delegate = pDelegate;
			characters = pCharacters;
		}

		@Override protected Boolean doInBackground(Void... unused)
		{
			for(T myChar : characters)
			{
				try
				{
					File dir = c.getDir("characters",Context.MODE_PRIVATE);
					File outFile = new File(dir, myChar.fluff.name);
					
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
			}
	
			return true;
		}
	
		@Override protected void onPostExecute(Boolean success)
		{
			if(delegate != null){delegate.characterSaveComplete(success);}
		}
	}	
}
