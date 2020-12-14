package sn.simplon.transfargent_android.ui.ajouter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sn.simplon.transfargent_android.R;

public class FaireTransactionFragment extends Fragment {

    private FairetransactionViewModel ajoutTransactionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ajoutTransactionViewModel =
                ViewModelProviders.of(this).get(FairetransactionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ajouter, container, false);

        return root;
    }
}