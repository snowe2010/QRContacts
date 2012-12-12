package com.snowe2010.android.qrcontacts.generate;

//import com.snowe2010.android.qrcontacts.MainActivity.SectionsPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.snowe2010.android.qrcontacts.R;

public class ChooseContact extends Activity {
	
	static final int PICK_CONTACT_REQUEST = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
//		setHasOptionsMenu(true);
		
		Button me = (Button) findViewById(R.id.me_contact);
		Button you = (Button) findViewById(R.id.someone_else_contact);
		me.setOnClickListener(onMe);
		you.setOnClickListener(onYou);
	}
	
	private View.OnClickListener onMe = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			Button me = (Button) findViewById(R.id.me_contact);
			
		}
	};
	
	private View.OnClickListener onYou = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//			pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.NUMBER);
			startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case PICK_CONTACT_REQUEST:
				//handle the contact returning here... i think
				Uri contactData = data.getData();
//		        Cursor c =  managedQuery(contactData, null, null, null, null);
				Cursor c = getContentResolver().query(Data.CONTENT_URI, new String[] {Data._ID, Phone.NUMBER, Phone.TYPE, Phone.LABEL}, Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "'", new String[] { String.valueOf(requestCode)}, null);
//		        if (c.moveToFirst()) {
		          String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//		          String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.Data.))
//		          String phoneNum = c.getString(c.getColumnIndex(ContactsContract.Contacts.))
		          Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
		          // TODO Whatever you want to do with the selected contact name.
//		        }
				Log.d("hey!", "Hey you made it here, that's good. :)");
				break;
			}
		} else {
			//handle failure?
			Log.w("ContactPicker", "Warning, activity didn't return contact correctly");
		}
	}
}



/** Sources:
 * http://stackoverflow.com/questions/866769/how-to-call-android-contacts-list
 * 
 */



/** Fragment {

	Button b = null;
	
	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.choose, container, false);
		b = (Button) getView().findViewById(R.id.testbutton);
		
		return b;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	
}
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// Create a new TextView and set its text to the fragment's section
	// number argument value.
	TextView textView = new TextView(getActivity());
	textView.setGravity(Gravity.CENTER);
	textView.setText(Integer.toString(getArguments().getInt(
			ARG_SECTION_NUMBER)));
	
	return textView;
}

}
}*/
