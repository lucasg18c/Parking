package com.slavik.parking.ui.pages.calculator;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.slavik.parking.R;
import com.slavik.parking.databinding.CalculatorFragmentBinding;

import java.util.Calendar;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel vm;
    private CalculatorFragmentBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CalculatorFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        vm.init(getContext());

        vm.getCobro().observe(requireActivity(),
                cobro -> binding.lblCobro.setText(cobro));

        vm.getTiempoEstadia().observe(requireActivity(),
                tiempo -> binding.lblTiempoEstadia.setText(tiempo));

        vm.getIngreso().observe(requireActivity(),
                hora -> binding.lblIngreso.setText(hora));

        vm.getSalida().observe(requireActivity(),
                hora -> binding.lblSalida.setText(hora));

        vm.getSalidaAhora().observe(requireActivity(),
                saleAhora -> binding.btnActualizarHora.setVisibility(saleAhora
                        ? View.GONE :
                        View.VISIBLE));

        vm.getTipoId().observe(requireActivity(), tipoID -> {
            int id = 0;
            switch (tipoID){
                case 0: id = R.id.rbAuto; break;
                case 1: id = R.id.rbCamioneta; break;
                case 2: id = R.id.rbMoto; break;
            }
            binding.rgTipo.check(id);
        });

        binding.rgTipo.setOnCheckedChangeListener((radioGroup, i) -> {
            int tvid = 0;

            switch (i){
                case R.id.rbAuto: tvid = 0; break;
                case R.id.rbCamioneta: tvid = 1; break;
                case R.id.rbMoto: tvid = 2; break;
            }

            vm.setTipo(tvid);
        });

        binding.lblIngreso.setOnClickListener(v -> {
            Calendar fechaActual = Calendar.getInstance();

            new TimePickerDialog(getContext(), (timePicker, hora, minuto) -> vm.setIngreso(hora, minuto),
                    fechaActual.get(Calendar.HOUR_OF_DAY),
                    fechaActual.get(Calendar.MINUTE),
                    false
            ).show();
        });
    }
}