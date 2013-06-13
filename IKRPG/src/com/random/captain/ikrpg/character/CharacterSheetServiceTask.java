package com.random.captain.ikrpg.character;

import android.graphics.*;
import java.util.*;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import com.random.captain.ikrpg.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class CharacterSheetServiceTask extends AsyncTask<PC, Void, Void>
{
	private Context context;
	private Canvas canvas;
	
	private Paint blackFluffLeft;
	private Paint blackFluffCenter;
	private Paint blackStatsLarge;
	private Paint blackStatsMedium;
	private Paint blackStatsSmall;
	private Paint skillName;
	private Paint phyPaint;
	private Paint agiPaint;
	private Paint intPaint;
	
	//name these better later, because these names suck.
	int dif = 34;
	int base = 292;
	int parentX = 986;
	int skillX = 1049;
	int totalX = 1104;
	
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
		writeModifiers(c);
	}
	
	private void setupPaints()
	{
		//Setup paints
		blackFluffLeft = new Paint();
		blackFluffLeft.setARGB(255,0,0,0);
		blackFluffLeft.setAntiAlias(true);
		blackFluffLeft.setTextSize(20);
		blackFluffLeft.setTypeface(Typeface.DEFAULT);

		blackFluffCenter = new Paint(blackFluffLeft);
		blackFluffCenter.setTextAlign(Paint.Align.CENTER);

		blackStatsLarge = new Paint(blackFluffCenter);
		blackStatsLarge.setTextSize(36);
		
		blackStatsMedium = new Paint(blackStatsLarge);
		blackStatsMedium.setTextSize(28);
		
		blackStatsSmall = new Paint(blackStatsLarge);
		blackStatsSmall.setTextSize(20);
		
		phyPaint = new Paint();
		phyPaint.setARGB(255,57,163,167);
		
		agiPaint = new Paint();
		agiPaint.setARGB(255,192,27,0);
		
		intPaint = new Paint();
		intPaint.setARGB(255,91,124,39);
		
		skillName = new Paint(blackFluffLeft);
		skillName.setTextSize(13);
	}
	
	private void writeFluff(PC c)
	{
		canvas.drawText(c.fluff.name,64,130,blackFluffLeft);
		canvas.drawText(c.fluff.sex,490,130,blackFluffCenter);
		canvas.drawText(c.fluff.characteristics,546,130,blackFluffLeft);
		canvas.drawText(c.fluff.weight,1056,130,blackFluffLeft);
		
		canvas.drawText(c.archetype.toString(),64,174,blackFluffLeft);
		canvas.drawText(c.race.toString(),226,174,blackFluffLeft);
		
		String careerString = "";
		for(Career career : c.careers)
		{
			if(careerString.length() > 0){careerString += "/";}
			careerString += career.toString();
		}
		
		Paint careerPaint = new Paint(blackFluffLeft);
		float careerFontSize = careerPaint.getTextSize();
		while(careerPaint.measureText(careerString) > 210){careerFontSize=careerFontSize-1;careerPaint.setTextSize(careerFontSize);}
		canvas.drawText(careerString, 378,174,careerPaint);
		
		canvas.drawText(c.fluff.faith, 594,174,blackFluffLeft);
		canvas.drawText(c.fluff.owningPlayer, 756,174,blackFluffLeft);
		canvas.drawText(c.fluff.height, 1056,174,blackFluffLeft);
		
		canvas.drawText(""+c.exp, 1279,174,blackFluffCenter);
		canvas.drawText(c.level.toString(), 1279,130,blackFluffCenter);
	}
	
	private void writeStats(PC c)
	{
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PHYSIQUE), 108, 654, blackStatsLarge);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.AGILITY), 108, 836, blackStatsLarge);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.INTELLECT), 108, 1018, blackStatsLarge);
		
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.SPEED), 232, 612, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.STRENGTH), 232, 688, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PROWESS), 232, 794, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.POISE), 232, 870, blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getBaseStat(Stat.PERCEPTION), 232, 1054, blackStatsMedium);
		
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PHYSIQUE), 170, 664, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.AGILITY), 170, 846, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.INTELLECT), 170, 1028, blackStatsSmall);

		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.SPEED), 276, 622, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.STRENGTH), 276, 700, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PROWESS), 276, 804, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.POISE), 276, 882, blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getMaxStat(Stat.PERCEPTION), 276, 1064, blackStatsSmall);
		
		canvas.drawText(""+c.statsBundle.getStat(Stat.DEFENSE), 726,900,blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getStat(Stat.ARMOR), 726,994,blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getStat(Stat.INITIATIVE), 726,1085,blackStatsMedium);
		canvas.drawText(""+c.statsBundle.getStat(Stat.COMMAND),726,1177,blackStatsMedium);
		
		//There's probably a better way to handle this than making Arcane a one-off, but oh well.
		int arcaneValue = c.statsBundle.getBaseStat(Stat.ARCANE);
		String arcaneString = "";
		
		arcaneString = arcaneValue > 0 ? ""+arcaneValue : "-";
		canvas.drawText(arcaneString, 232, 976, blackStatsMedium);
		arcaneValue = c.statsBundle.getMaxStat(Stat.ARCANE);
		arcaneString = arcaneValue > 0 ? ""+arcaneValue : "-";
		canvas.drawText(arcaneString, 276, 986, blackStatsSmall);
		
		int phyStat = c.statsBundle.getBaseStat(Stat.PHYSIQUE);
		if(phyStat<10){canvas.drawCircle(893,988,11,phyPaint);}
		if(phyStat<9){canvas.drawCircle(866,997,11,phyPaint);}
		if(phyStat<8){canvas.drawCircle(886,1010,11,phyPaint);}
		if(phyStat<7){canvas.drawCircle(866,1023,11,phyPaint);}
		if(phyStat<6){canvas.drawCircle(890,1033,10,phyPaint);}
		if(phyStat<5){canvas.drawCircle(874,1048,10,phyPaint);}
		
		int agiStat = c.statsBundle.getBaseStat(Stat.AGILITY);
		if(agiStat<8){canvas.drawCircle(999,1021,11,agiPaint);}
		if(agiStat<7){canvas.drawCircle(998,996,11,agiPaint);}
		if(agiStat<6){canvas.drawCircle(978,1012,10,agiPaint);}
		if(agiStat<5){canvas.drawCircle(973,991,10,agiPaint);}
		if(agiStat<4){canvas.drawCircle(957,1012,9,agiPaint);}
		
		int intStat = c.statsBundle.getBaseStat(Stat.INTELLECT);
		if(intStat<8){canvas.drawCircle(931,1114,11,intPaint);}
		if(intStat<7){canvas.drawCircle(953,1124,11,intPaint);}
		if(intStat<6){canvas.drawCircle(948,1099,10,intPaint);}
		if(intStat<5){canvas.drawCircle(969,1104,10,intPaint);}
		if(intStat<4){canvas.drawCircle(957,1080,9,intPaint);}
	}
	
	private void writeSkills(PC c)
	{
		//start with hard-written skills
		Collection<Pair<Skill,Integer>> trainedSkills = handleHardWrittenSkills(c);
		
		int militaryIndex = 4;
		int occupIndex = 10;
		
		for(Pair<Skill,Integer> skill : trainedSkills)
		{
			int count = -1;
			//determine y value
			if(skill.first.isMilitary()){count=militaryIndex; militaryIndex++;}
			else{count=occupIndex; occupIndex++;}
			
			Stat bStat = skill.first.governingStat();
			if(bStat != null)
			{
				canvas.drawText(""+c.statsBundle.getStat(bStat), parentX, base+count*dif, blackStatsSmall);
				canvas.drawText(""+c.skillsBundle.getSkillLevel(skill.first), totalX, base+count*dif, blackStatsSmall);
			}
			else
			{
				canvas.drawText("*", parentX, base+count*dif, blackStatsSmall);
				canvas.drawText("*", totalX, base+count*dif, blackStatsSmall);
			}
			
			String shortSkillName;
			if(bStat == null){shortSkillName = "SOCIAL";}
			else{shortSkillName = bStat.shortName();}
			canvas.drawText(""+skill.second, skillX, base+count*dif, blackStatsSmall);
			canvas.drawText(skill.first.toString()+" ("+shortSkillName+")", 800, base+count*dif-2, skillName);
		}
	}
	
	//Probably going way overboard, but I don't like modifying variables without warning.
	private Collection<Pair<Skill,Integer>> handleHardWrittenSkills(PC c)
	{
		Collection<Pair<Skill,Integer>> trainedSkills =c.skillsBundle.getTrainedSkills();
		Collection<Pair<Skill,Integer>> trainedSkillsCopy = new ArrayList<Pair<Skill,Integer>>(trainedSkills);
		
		canvas.drawText(""+c.statsBundle.getStat(Stat.PROWESS),parentX,base,blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getStat(Stat.PROWESS),parentX,base+dif,blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getStat(Stat.POISE),parentX,base+dif*2,blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getStat(Stat.POISE),parentX,base+dif*3,blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getStat(Stat.PERCEPTION),parentX,base+dif*7,blackStatsSmall);
		canvas.drawText(""+c.statsBundle.getStat(Stat.AGILITY),parentX,base+dif*8,blackStatsSmall);
		canvas.drawText("*",986,base+dif*9,blackStatsSmall);
		
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.HAND_WEAPON.make()),totalX,base,blackStatsSmall);
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.GREAT_WEAPON.make()),totalX,base+dif,blackStatsSmall);
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.PISTOL.make()),totalX,base+dif*2,blackStatsSmall);
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.RIFLE.make()),totalX,base+dif*3,blackStatsSmall);
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.DETECTION.make()),totalX,base+dif*7,blackStatsSmall);
		canvas.drawText(""+c.skillsBundle.getSkillLevel(SkillEnum.SNEAK.make()),totalX,base+dif*8,blackStatsSmall);
		canvas.drawText("*",totalX,base+dif*9,blackStatsSmall);
		
		for(Pair<Skill,Integer> skill : trainedSkills)
		{
			int count = -1;
			
			switch(skill.first.skillEnum())
			{
				case HAND_WEAPON: count = 0; break;
				case GREAT_WEAPON: count = 1; break;
				case PISTOL: count = 2; break;
				case RIFLE: count = 3; break;
				case DETECTION: count = 7; break;
				case SNEAK: count = 8; break;
				case COMMAND: count = 9; break;
				default: count = -1; break;
			}
			
			if(count > -1)
			{
				canvas.drawText(""+skill.second, skillX, base+count*dif, blackStatsSmall);
				trainedSkillsCopy.remove(skill);
			}
		}
		
		return trainedSkillsCopy;
	}
	
	private void writeAbilities(PC c)
	{
		
	}
	
	private void writeModifiers(PC c)
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
