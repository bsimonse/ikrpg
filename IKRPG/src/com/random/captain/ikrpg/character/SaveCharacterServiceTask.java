package com.random.captain.ikrpg.character;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;

public class SaveCharacterServiceTask<T extends zzBaseCharacter> extends AsyncTask<T, Void, Boolean>
{
	public interface Delegate
	{
		public void characterSaveComplete(boolean succeeded);
	}
	
	private Delegate delegate;
	private Context c;
	
	public SaveCharacterServiceTask(Context pContext, Delegate pDelegate)
	{
		delegate = pDelegate;
		c = pContext;
	}
	
	@Override protected Boolean doInBackground(T... characters)
	{
		for(T myChar : characters)
		{
			try
			{
				Log.i("IKRPG","Let's save that character!");
				File dir = c.getDir("characters",Context.MODE_PRIVATE);
				File outFile = new File(dir, myChar.fluff.name);
				
				String outputJson = myChar.toJson();
				FileOutputStream os = new FileOutputStream(outFile);
				os.write(outputJson.getBytes("UTF-8"));
					
				os.flush();os.close();
				Log.i("IKRPG","Saved "+myChar.fluff.name+"!");
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
