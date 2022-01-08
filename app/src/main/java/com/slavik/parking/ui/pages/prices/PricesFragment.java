package com.slavik.parking.ui.pages.prices;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slavik.parking.R;
import com.slavik.parking.databinding.CalculatorFragmentBinding;
import com.slavik.parking.databinding.PricesFragmentBinding;
import com.slavik.parking.ui.pages.calculator.CalculatorViewModel;
import com.slavik.parking.util.TextWatcherLite;

public class PricesFragment extends Fragment {

    private PricesViewModel vm;
    private PricesFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = PricesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(PricesViewModel.class);
        vm.init(requireActivity());

        vm.getAuto().observe(requireActivity(), precio -> binding.txtAuto.setText(String.valueOf(precio)));
        vm.getCamioneta().observe(requireActivity(), precio -> binding.txtCamioneta.setText(String.valueOf(precio)));
        vm.getMoto().observe(requireActivity(), precio -> binding.txtMoto.setText(String.valueOf(precio)));

        binding.txtAuto.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(CharSequence nuevoTexto) {
                vm.setAuto(nuevoTexto);
            }
        });

        binding.txtCamioneta.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(CharSequence nuevoTexto) {
                vm.setCamioneta(nuevoTexto);
            }
        });

        binding.txtMoto.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(CharSequence nuevoTexto) {
                vm.setMoto(nuevoTexto);
            }
        });
    }
}