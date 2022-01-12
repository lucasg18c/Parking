package com.slavik.parking.ui.pages.prices;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.slavik.parking.databinding.PricesFragmentBinding;
import com.slavik.parking.util.Teclado;
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
        vm.init();

        LifecycleOwner activity = requireActivity();

        binding.txtAuto.setText(vm.getAuto().getValue());
        binding.txtCamioneta.setText(vm.getCamioneta().getValue());
        binding.txtMoto.setText(vm.getMoto().getValue());

        vm.getError().observe(activity, error -> {
            if (error.length() > 0) {
                Toast.makeText(
                        (Context) activity,
                        error,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        binding.txtAuto.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(String nuevoTexto) {
                vm.setPrecioAuto(nuevoTexto);
            }
        });

        binding.txtCamioneta.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(String nuevoTexto) {
                vm.setPrecioCamioneta(nuevoTexto);
            }
        });

        binding.txtMoto.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(String nuevoTexto) {
                vm.setPrecioMoto(nuevoTexto);
            }
        });

        binding.pricesFondo.setOnClickListener(v -> Teclado.cerrar(view));
    }

    @Override
    public void onPause() {
        super.onPause();
        vm.updatePrecios();
    }
}