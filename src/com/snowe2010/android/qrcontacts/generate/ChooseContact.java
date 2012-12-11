package com.snowe2010.android.qrcontacts.generate;

//import com.snowe2010.android.qrcontacts.MainActivity.SectionsPagerAdapter;

import com.snowe2010.android.qrcontacts.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseContact extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
//		setHasOptionsMenu(true);
		
		Button me = (Button) findViewById(R.id.me_contact);
		Button you = (Button) findViewById(R.id.someone_else_contact);
		me.setOnClickListener(onMe);
	}
	
	private View.OnClickListener onMe = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			Button me = (Button) findViewById(R.id.me_contact);
			
		}
	};
}




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
