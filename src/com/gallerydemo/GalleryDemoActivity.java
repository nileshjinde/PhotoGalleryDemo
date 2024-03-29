package com.gallerydemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GalleryDemoActivity extends Activity {

	private ImageView selectedImageView;

	private ImageView leftArrowImageView;

	private ImageView rightArrowImageView;

	private Gallery gallery;

	private int selectedImagePosition = 0;

	private List<Drawable> drawables;

	private GalleryImageAdapter galImageAdapter;
	Button mButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		getDrawablesList();
		mButton=(Button)findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadPhoto();
				
			}
		});
		
	//	setupUI();
	}

	public void loadPhoto() {
		final Dialog custom = new Dialog(this);
		custom.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		custom.setContentView(R.layout.photo_popup_view);
		
	//	custom.getWindow().setBackgroundDrawable(new ColorDrawable(color.transparent));
		
		//-----------------------------------------------------------------------------
		selectedImageView = (ImageView) custom.findViewById(R.id.selected_imageview);
		leftArrowImageView = (ImageView) custom.findViewById(R.id.left_arrow_imageview);
		rightArrowImageView = (ImageView) custom.findViewById(R.id.right_arrow_imageview);
		gallery = (Gallery) custom.findViewById(R.id.gallery);

		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition > 0) {
					--selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition < drawables.size() - 1) {
					++selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);

			}
		});

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				selectedImagePosition = pos;

				if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

				} else if (selectedImagePosition == 0) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

				} else if (selectedImagePosition == drawables.size() - 1) {

					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
				}

				changeBorderForSelectedImage(selectedImagePosition);
				setSelectedImage(selectedImagePosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		galImageAdapter = new GalleryImageAdapter(this, drawables);

		gallery.setAdapter(galImageAdapter);

		if (drawables.size() > 0) {

			gallery.setSelection(selectedImagePosition, false);

		}

		if (drawables.size() == 1) {

			rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
		}
		//----------------------------------------------------------------------------
		
		
		RelativeLayout lnrImagePopup=(RelativeLayout) custom.findViewById(R.id.rlTTop);
		lnrImagePopup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				custom.cancel();
			}
		});
		
		/*image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				custom.cancel();
			}
		});*/
	
		
		custom.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_BACK){
					custom.cancel();
					return true;
				}else if(keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_MENU){
					return true;
				}
				return false;
			}
		});
		custom.show();
	}
	
	private void setupUI() {

		selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
		leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
		rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		gallery = (Gallery) findViewById(R.id.gallery);

		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition > 0) {
					--selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition < drawables.size() - 1) {
					++selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);

			}
		});

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				selectedImagePosition = pos;

				if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

				} else if (selectedImagePosition == 0) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

				} else if (selectedImagePosition == drawables.size() - 1) {

					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
				}

				changeBorderForSelectedImage(selectedImagePosition);
				setSelectedImage(selectedImagePosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		galImageAdapter = new GalleryImageAdapter(this, drawables);

		gallery.setAdapter(galImageAdapter);

		if (drawables.size() > 0) {

			gallery.setSelection(selectedImagePosition, false);

		}

		if (drawables.size() == 1) {

			rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
		}

	}

	private void changeBorderForSelectedImage(int selectedItemPos) {

		int count = gallery.getChildCount();

		for (int i = 0; i < count; i++) {

			ImageView imageView = (ImageView) gallery.getChildAt(i);
			imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_border));
			imageView.setPadding(3, 3, 3, 3);

		}

		ImageView imageView = (ImageView) gallery.getSelectedView();
		imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_image_border));
		imageView.setPadding(3, 3, 3, 3);
	}

	private void getDrawablesList() {

		drawables = new ArrayList<Drawable>();
		drawables.add(getResources().getDrawable(R.drawable.natureimage1));
		drawables.add(getResources().getDrawable(R.drawable.natureimage2));
		drawables.add(getResources().getDrawable(R.drawable.natureimage3));
		drawables.add(getResources().getDrawable(R.drawable.natureimage4));
		drawables.add(getResources().getDrawable(R.drawable.natureimage5));
		drawables.add(getResources().getDrawable(R.drawable.natureimage6));
		drawables.add(getResources().getDrawable(R.drawable.natureimage7));
		drawables.add(getResources().getDrawable(R.drawable.natureimage8));
		drawables.add(getResources().getDrawable(R.drawable.natureimage9));
		drawables.add(getResources().getDrawable(R.drawable.natureimage10));
		drawables.add(getResources().getDrawable(R.drawable.natureimage11));
		drawables.add(getResources().getDrawable(R.drawable.natureimage12));
		drawables.add(getResources().getDrawable(R.drawable.natureimage13));
		drawables.add(getResources().getDrawable(R.drawable.natureimage14));
		drawables.add(getResources().getDrawable(R.drawable.natureimage15));

	}

	private void setSelectedImage(int selectedImagePosition) {

		BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
		Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight() * 0.9), (int) (bd.getIntrinsicWidth() * 0.7), false);
		selectedImageView.setImageBitmap(b);
		selectedImageView.setScaleType(ScaleType.FIT_XY);

	}
}