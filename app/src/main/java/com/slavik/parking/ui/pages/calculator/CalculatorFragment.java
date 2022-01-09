package com.slavik.parking.ui.pages.calculator;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.slavik.parking.databinding.CalculatorFragmentBinding;

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

        vm = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        vm.init();

        vm.getCobro().observe(requireActivity(),
                cobro -> binding.lblCobro.setText(cobro));

        vm.getTiempoEstadia().observe(requireActivity(),
                tiempo -> binding.lblTiempoEstadia.setText(tiempo));

        vm.getHoraIngreso().observe(requireActivity(),
                hora -> binding.lblIngreso.setText(hora));

        vm.getHoraSalida().observe(requireActivity(),
                hora -> binding.lblSalida.setText(hora));

        vm.getSalidaAhora().observe(requireActivity(),
                saleAhora -> binding.btnActualizarHora.setVisibility(saleAhora
                        ? View.GONE :
                        View.VISIBLE));

        vm.getTipoId().observe(requireActivity(), tipoID -> binding.rgTipo.check(tipoID));

        binding.rgTipo.setOnCheckedChangeListener((radioGroup, i) -> vm.setTipo(i));

        binding.lblIngreso.setOnClickListener(v -> {
            Calendar fechaActual = Calendar.getInstance();

            new TimePickerDialog(getContext(), (timePicker, hora, minuto) -> vm.setIngreso(hora, minuto),
                    fechaActual.get(Calendar.HOUR_OF_DAY),
                    fechaActual.get(Calendar.MINUTE),
                    false //todo Agregar opción para cambiarlo
            ).show();
        });
    }
}