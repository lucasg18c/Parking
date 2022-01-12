package com.slavik.parking.ui.pages.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.slavik.parking.databinding.CalculatorFragmentBinding;
import com.slavik.parking.util.Teclado;
import com.slavik.parking.util.TextWatcherLite;

import java.util.Calendar;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel vm;
    private CalculatorFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CalculatorFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Iniciar Viewmodel
        vm = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        vm.init();

        // Subscribir observers
        LifecycleOwner activity = requireActivity();

        vm.getCobro().observe(activity,
                cobro -> binding.lblCobro.setText(cobro));

        vm.getTiempoEstadia().observe(activity,
                tiempo -> binding.lblTiempoEstadia.setText(tiempo));

        vm.getTipoId().observe(activity, tipoID -> binding.rgTipo.check(tipoID));

        // Setear valores por defecto
        binding.txtIngreso.setHour(vm.getCalendarIngreso().get(Calendar.HOUR_OF_DAY));
        binding.txtIngreso.setMinutes(vm.getCalendarIngreso().get(Calendar.MINUTE));

        // Agregar listeners para eventos
        binding.txtIngreso.addTextChangedListener(new TextWatcherLite() {
            @Override
            protected void onTextChanged(String nuevoTexto) {
                vm.setIngreso(
                        binding.txtIngreso.getHour(),
                        binding.txtIngreso.getMinutes()
                );
            }
        });

        binding.calculatorFondo.setOnClickListener(v -> {
            Teclado.cerrar(view);
            binding.txtIngreso.clearFocus();
        });

        binding.rgTipo.setOnCheckedChangeListener((radioGroup, i) -> vm.setTipo(i));
    }
}