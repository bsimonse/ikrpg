package com.random.captain.ikrpg.character;

import java.io.*;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class LoadCharacterServiceTask<T extends zzBaseCharacter> extends AsyncTask<String, Void, Set<T>>
{
	public interface Delegate<T extends zzBaseCharacter>
	{
		public void charactersLoaded(Set<T> characters);
	}
	
	private Delegate<T> delegate;
	private Context c;
	private Class<T> charClass;
	private Set<T> characters = new HashSet<T>();
	
	public LoadCharacterServiceTask(Context pContext, Class<T> pCharClass, Delegate<T> pDelegate)
	{
		delegate = pDelegate;
		charClass = pCharClass;
		c = pContext;
	}

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
