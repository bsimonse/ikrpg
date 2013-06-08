package com.random.captain.ikrpg.character;

import android.graphics.*;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.random.captain.ikrpg.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;


public class CharacterSheetServiceTask extends AsyncTask<PC, Void, Void>
{
	private Context context;
	private Canvas canvas;
	private Paint blackTextLeft;
	private Paint blackTextCenter;
	
	public CharacterSheetServiceTask(Context pContext)
	{
		context = pContext.getApplicationContext();
	}
	
	@Override public Void doInBackground(PC... pcs)
	{
		PC myChar = pcs[0];
		if(myChar != null)
		{
			try
			{
				Drawable baseSheet = context.getResources().getDrawable(R.drawable.char_sheet_1);
				int width = baseSheet.getIntrinsicWidth();
				int height = baseSheet.getIntrinsicHeight();
				baseSheet.setBounds(0,0,width,height);
				Bitmap b = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
				canvas = new Canvas();
				canvas.setBitmap(b);
				
				baseSheet.draw(canvas);
				
				blackTextLeft = new Paint();
				blackTextLeft.setARGB(255,0,0,0);
				blackTextLeft.setAntiAlias(true);
				blackTextLeft.setTextSize(10);
				blackTextLeft.setTypeface(Typeface.DEFAULT);
				
				blackTextCenter = new Paint(blackTextLeft);
				blackTextCenter.setTextAlign(Paint.Align.CENTER);
				
				fillInCharacter(myChar);
				
				//Make sure to make name file safe before shipping
				File f = new File(Environment.getExternalStorageDirectory(), myChar.fluff.name+".png");
				f.createNewFile();
				
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				b.compress(Bitmap.CompressFormat.PNG, 0, stream);
				FileOutputStream os = new FileOutputStream(f);
				os.write(stream.toByteArray());
				
				stream.flush();stream.close();
				os.flush();os.close();
				Log.i("IKRPG","Hooray!");
			}
			catch(Exception e)
			{
				Log.i("IKRPG","Drat. "+e.getLocalizedMessage()+"\n"+e.getStackTrace());
			}
		}
		
		return null;
	}
	
	private void fillInCharacter(PC c)
	{
		writeFluff(c);
		writeStats(c);
		writeSkills(c);
		writeAbilities(c);
	}
	
	private void writeFluff(PC c)
	{
		canvas.drawText(c.fluff.name,32,65,blackTextLeft);
		canvas.drawText(c.fluff.sex,245,65,blackTextCenter);
		canvas.drawText(c.fluff.characteristics,273,65,blackTextLeft);
		canvas.drawText(c.fluff.weight,528,65,blackTextLeft);
		
		canvas.drawText(c.archetype.toString(),32,87,blackTextLeft);
		canvas.drawText(c.race.toString(),113,87,blackTextLeft);
		
		String careerString = "";
		for(Career career : c.careers)
		{
			if(careerString.length() > 0){careerString += "/";}
			careerString += career.toString();
		}
		canvas.drawText(careerString, 189,87,blackTextLeft);
		
		canvas.drawText(c.fluff.faith, 297,87,blackTextLeft);
		canvas.drawText(c.fluff.owningPlayer, 378,87,blackTextLeft);
		canvas.drawText(c.fluff.height, 528,87,blackTextLeft);
		}
	
	private void writeStats(PC c)
	{
		
	}
	
	private void writeSkills(PC c)
	{
		
	}
	
	private void writeAbilities(PC c)
	{
		
	}
	
	public static PC getDummyCharacter()
	{
		PC myC = new PC();
		
		Fluff fluff = new Fluff();
		fluff.name = "Felix";
		fluff.sex = "Male";
		fluff.characteristics = "Battle scar";
		fluff.weight = "185 lbs";
		fluff.faith = "Morrowan";
		fluff.owningPlayer = "You";
		fluff.height = "5' 11\"";
		myC.fluff = fluff;
		
		myC.race = Race.HUMAN;
		
		myC.archetype = Archetype.MIGHTY;
		
		Set<Career> careers = new HashSet<Career>(2);
		careers.add(Career.ALCHEMIST);
		careers.add(Career.PIRATE);
		myC.careers = careers;
		
		return myC;
	}
}
