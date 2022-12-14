// Generated by view binder compiler. Do not edit!
package com.example.eliza.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eliza.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonMakeSell;

  @NonNull
  public final ImageView imageLogo;

  @NonNull
  public final RecyclerView recyclerViewInventory;

  @NonNull
  public final TextView textViewInventoryTitle;

  @NonNull
  public final TextView textViewNameTitle;

  @NonNull
  public final TextView textViewQuantityTitle;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonMakeSell,
      @NonNull ImageView imageLogo, @NonNull RecyclerView recyclerViewInventory,
      @NonNull TextView textViewInventoryTitle, @NonNull TextView textViewNameTitle,
      @NonNull TextView textViewQuantityTitle) {
    this.rootView = rootView;
    this.buttonMakeSell = buttonMakeSell;
    this.imageLogo = imageLogo;
    this.recyclerViewInventory = recyclerViewInventory;
    this.textViewInventoryTitle = textViewInventoryTitle;
    this.textViewNameTitle = textViewNameTitle;
    this.textViewQuantityTitle = textViewQuantityTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonMakeSell;
      Button buttonMakeSell = ViewBindings.findChildViewById(rootView, id);
      if (buttonMakeSell == null) {
        break missingId;
      }

      id = R.id.imageLogo;
      ImageView imageLogo = ViewBindings.findChildViewById(rootView, id);
      if (imageLogo == null) {
        break missingId;
      }

      id = R.id.recyclerViewInventory;
      RecyclerView recyclerViewInventory = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewInventory == null) {
        break missingId;
      }

      id = R.id.textViewInventoryTitle;
      TextView textViewInventoryTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewInventoryTitle == null) {
        break missingId;
      }

      id = R.id.textViewNameTitle;
      TextView textViewNameTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewNameTitle == null) {
        break missingId;
      }

      id = R.id.textViewQuantityTitle;
      TextView textViewQuantityTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewQuantityTitle == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, buttonMakeSell, imageLogo,
          recyclerViewInventory, textViewInventoryTitle, textViewNameTitle, textViewQuantityTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
