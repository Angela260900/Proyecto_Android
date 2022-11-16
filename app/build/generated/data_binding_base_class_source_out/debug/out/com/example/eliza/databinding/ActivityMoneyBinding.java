// Generated by view binder compiler. Do not edit!
package com.example.eliza.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eliza.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMoneyBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guideLine;

  @NonNull
  public final ImageView imageLogo;

  @NonNull
  public final TextView textViewAssets;

  @NonNull
  public final TextView textViewAssetsValue;

  @NonNull
  public final TextView textViewPassives;

  @NonNull
  public final TextView textViewPassivesValue;

  @NonNull
  public final TextView textViewPatrimony;

  @NonNull
  public final TextView textViewPatrimonyValue;

  private ActivityMoneyBinding(@NonNull ConstraintLayout rootView, @NonNull Guideline guideLine,
      @NonNull ImageView imageLogo, @NonNull TextView textViewAssets,
      @NonNull TextView textViewAssetsValue, @NonNull TextView textViewPassives,
      @NonNull TextView textViewPassivesValue, @NonNull TextView textViewPatrimony,
      @NonNull TextView textViewPatrimonyValue) {
    this.rootView = rootView;
    this.guideLine = guideLine;
    this.imageLogo = imageLogo;
    this.textViewAssets = textViewAssets;
    this.textViewAssetsValue = textViewAssetsValue;
    this.textViewPassives = textViewPassives;
    this.textViewPassivesValue = textViewPassivesValue;
    this.textViewPatrimony = textViewPatrimony;
    this.textViewPatrimonyValue = textViewPatrimonyValue;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMoneyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMoneyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_money, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMoneyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.guideLine;
      Guideline guideLine = ViewBindings.findChildViewById(rootView, id);
      if (guideLine == null) {
        break missingId;
      }

      id = R.id.imageLogo;
      ImageView imageLogo = ViewBindings.findChildViewById(rootView, id);
      if (imageLogo == null) {
        break missingId;
      }

      id = R.id.textViewAssets;
      TextView textViewAssets = ViewBindings.findChildViewById(rootView, id);
      if (textViewAssets == null) {
        break missingId;
      }

      id = R.id.textViewAssetsValue;
      TextView textViewAssetsValue = ViewBindings.findChildViewById(rootView, id);
      if (textViewAssetsValue == null) {
        break missingId;
      }

      id = R.id.textViewPassives;
      TextView textViewPassives = ViewBindings.findChildViewById(rootView, id);
      if (textViewPassives == null) {
        break missingId;
      }

      id = R.id.textViewPassivesValue;
      TextView textViewPassivesValue = ViewBindings.findChildViewById(rootView, id);
      if (textViewPassivesValue == null) {
        break missingId;
      }

      id = R.id.textViewPatrimony;
      TextView textViewPatrimony = ViewBindings.findChildViewById(rootView, id);
      if (textViewPatrimony == null) {
        break missingId;
      }

      id = R.id.textViewPatrimonyValue;
      TextView textViewPatrimonyValue = ViewBindings.findChildViewById(rootView, id);
      if (textViewPatrimonyValue == null) {
        break missingId;
      }

      return new ActivityMoneyBinding((ConstraintLayout) rootView, guideLine, imageLogo,
          textViewAssets, textViewAssetsValue, textViewPassives, textViewPassivesValue,
          textViewPatrimony, textViewPatrimonyValue);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
