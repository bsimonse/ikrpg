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
	
	private Paint blackFluffLeft;
	private Paint blackFluffCenter;
	private Paint blackStatsLarge;
	private Paint blackStatsMedium;
	private Paint blackStatsSmall;
	
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
		setupPaints();
		writeFluff(c);
		writeStats(c);
		writeSkills(c);
		writeAbilities(c);
	}
	
	private void setupPaints()
	{
		//Setup paints
		blackFluffLeft = new Paint();
		blackFluffLeft.setARGB(255,0,0,0);
		blackFluffLeft.setAntiAlias(true);
		blackFluffLeft.setTextSize(10);
		blackFluffLeft.setTypeface(Typeface.DEFAULT);

		blackFluffCenter = new Paint(blackFluffLeft);
		blackFluffCenter.setTextAlign(Paint.Align.CENTER);

		blackStatsLarge = new Paint(blackFluffCenter);
		blackStatsLarge.setTextSize(18);
		
		blackStatsMedium = new Paint(blackStatsLarge);
		blackStatsMedium.setTextSize(14);
		
		blackStatsSmall = new Paint(blackStatsLarge);
		blackStatsSmall.setTextSize(10);
	}
	
	private void writeFluff(PC c)
	{
		canvas.drawText(c.fluff.name,32,65,blackFluffLeft);
		canvas.drawText(c.fluff.sex,245,65,blackFluffCenter);
		canvas.drawText(c.fluff.characteristics,273,65,blackFluffLeft);
		canvas.drawText(c.fluff.weight,528,65,blackFluffLeft);
		
		canvas.drawText(c.archetype.toString(),32,87,blackFluffLeft);
		canvas.drawText(c.race.toString(),113,87,blackFluffLeft);
		
		String careerString = "";
		for(Career career : c.careers)
		{
			if(careerString.length() > 0){careerString += "/";}
			careerString += career.toString();
		}
		canvas.drawText(careerString, 189,87,blackFluffLeft);
		
		canvas.drawText(c.fluff.faith, 297,87,blackFluffLeft);
		canvas.drawText(c.fluff.owningPlayer, 378,87,blackFluffLeft);
		canvas.drawText(c.fluff.height, 528,87,blackFluffLeft);
		}
	
	private void writeStats(PC c)
	{
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PHYSIQUE), 54, 327, blackStatsLarge);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.AGILITY), 54, 418, blackStatsLarge);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.INTELLECT), 54, 509, blackStatsLarge);
		
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.SPEED), 116, 306, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.STRENGTH), 116, 344, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PROWESS), 116, 397, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.POISE), 116, 435, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.ARCANE), 117, 488, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PERCEPTION), 117, 526, blackStatsMedium);
		
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PHYSIQUE), 85, 332, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.AGILITY), 85, 423, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.INTELLECT), 85, 514, blackStatsSmall);

		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.SPEED), 138, 311, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.STRENGTH), 138, 350, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PROWESS), 138, 402, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.POISE), 138, 441, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.ARCANE), 138, 493, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PERCEPTION), 138, 532, blackStatsSmall);
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
		
		myC.statsBundle = new zzStatsBundle(Race.HUMAN.startStats(), Race.HUMAN.heroStats());
		
		return myC;
	}
}
