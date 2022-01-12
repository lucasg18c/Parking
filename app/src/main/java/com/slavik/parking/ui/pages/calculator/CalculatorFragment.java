package com.slavik.parking.ui.pages.calculator;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.slavik.parking.databinding.CalculatorFragmentBinding;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        vm.init();

        vm.getCobro().observe(requireActivity(),
                cobro -> binding.lblCobro.setText(cobro));

        vm.getTiempoEstadia().observe(requireActivity(),
                tiempo -> binding.lblTiempoEstadia.setText(tiempo));

//        vm.getHoraIngreso().observe(requireActivity(), hora -> {
//            binding.lblIngreso.setText(hora);
//        });

        vm.getTipoId().observe(requireActivity(), tipoID -> binding.rgTipo.check(tipoID));

//        binding.tpIngreso.setOnTimeChangedListener((timePicker, hora, minuto)
//                -> vm.setIngreso(hora, minuto));
//
//        binding.tpIngreso.setHour(vm.getCalendarIngreso().get(Calendar.HOUR_OF_DAY));
//        binding.tpIngreso.setMinute(vm.getCalendarIngreso().get(Calendar.MINUTE));

        binding.rgTipo.setOnCheckedChangeListener((radioGroup, i) -> vm.setTipo(i));

        binding.txtIngreso.setHour(vm.getCalendarIngreso().get(Calendar.HOUR_OF_DAY));
        binding.txtIngreso.setMinutes(vm.getCalendarIngreso().get(Calendar.MINUTE));
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
            InputMethodManager inputMethodManager = (InputMethodManager) requireActivity()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            binding.txtIngreso.clearFocus();
        });
//
//        binding.lblIngreso.setOnClickListener(v -> {
//            Calendar fechaActual = Calendar.getInstance();
//
//            new TimePickerDialog(getContext(), (timePicker, hora, minuto) -> vm.setIngreso(hora, minuto),
//                    fechaActual.get(Calendar.HOUR_OF_DAY),
//                    fechaActual.get(Calendar.MINUTE),
//                    false
//            ).show();
//        });
    }
}