package com.random.captain.ikrpg;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.CharacterSheetService;
import com.random.captain.ikrpg.character.CharacterStorageService;
import com.random.captain.ikrpg.character.PC;

public class CharacterHomeActivity extends Activity
{
	private PC billy;
	
	@Override
	public void onCreate(Bundle b)
	{
        super.onCreate(b);
        setContentView(R.layout.act_character_home);
		
		billy = (PC)getIntent().getExtras().get("CHAR");
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		if(billy != null)
		{
			TextView tv = (TextView)findViewById(R.id.char_home_name);
			tv.setText(billy.fluff().name);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu pMenu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.character_menu, pMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem pItem)
	{
		switch(pItem.getItemId())
		{
			case R.id.print_character:
				
				CharacterSheetService css = new CharacterSheetService(this);
				css.drawCharacterSheet(billy, new CharacterSheetService.Delegate(){
					@Override public void characterSheetComplete(boolean worked)
					{
						if(worked)
						{new Toast(CharacterHomeActivity.this).makeText(CharacterHomeActivity.this, billy.fluff().name+" printed!", Toast.LENGTH_SHORT).show();}
						else
						{new Toast(CharacterHomeActivity.this).makeText(CharacterHomeActivity.this, "Couldn't print character sheet.", Toast.LENGTH_LONG).show();}
					}
				});
				
				return true;
			case R.id.delete_character:
			
				//Are you sure?  Of course you're sure.
				
				CharacterStorageService s = new CharacterStorageService(this);
				s.deleteCharacter(billy.fluff().name, new CharacterStorageService.DeleteDelegate(){
					@Override public void characterDeleted(boolean worked)
					{
						if(worked)
						{
							new Toast(CharacterHomeActivity.this).makeText(CharacterHomeActivity.this, billy.fluff().name+" deleted!", Toast.LENGTH_SHORT).show();
							CharacterHomeActivity.this.finish();
						}
						else
						{new Toast(CharacterHomeActivity.this).makeText(CharacterHomeActivity.this, "Couldn't... delete character?", Toast.LENGTH_LONG).show();}
					}
				});
				return true;
			default:
				return super.onOptionsItemSelected(pItem);
		}
	}
}
