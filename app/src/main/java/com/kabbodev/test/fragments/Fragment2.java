package com.kabbodev.test.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kabbodev.test.R;
import com.kabbodev.test.databinding.Fragment2Binding;
import com.kabbodev.test.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setupTheme();
        setupClickListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupTheme() {
        String uuid = UUID.randomUUID().toString().substring(0, 7).replace('-', '4');
        binding.referCode.setText(uuid);
    }

    private void setupClickListeners() {
        binding.copybtn.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", binding.referCode.getText());
            clipboardManager.setPrimaryClip(clip);
            Utils.showSnackbar(binding.rootLayout, "Copied Refer Code");
        });
        binding.shareLayout.setOnClickListener(v -> shareAppFun());
    }

    private void shareAppFun() {
        try {
            Intent shareAppIntent = new Intent(Intent.ACTION_SEND);
            shareAppIntent.setType("text/plain");
            shareAppIntent.putExtra(Intent.EXTRA_TEXT, "Share this message. My Refer Code : " + binding.referCode.getText());
            startActivity(Intent.createChooser(shareAppIntent, getString(R.string.app_name)));
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show();
        }
    }

}