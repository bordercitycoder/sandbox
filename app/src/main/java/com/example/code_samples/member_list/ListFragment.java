package com.example.code_samples.member_list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.code_samples.R;
import com.example.code_samples.R2;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ListFragment extends Fragment {

    @BindView(R2.id.memberRecyclerView)
    RecyclerView listView;
    @BindView(R2.id.errorTextView)
    TextView errorTextView;
    @BindView(R2.id.loadingProgressBar)
    View loadingProgressBar;

    private Unbinder unbinder;
    private ListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_list_screen, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new MemberListAdapter(viewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    private void observeViewModel() {

        viewModel.getMembers().observe(this, members -> {
            if (members != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });


        viewModel.getError().observe(this, isError -> {
            if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(R.string.api_error_repos);
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });


        viewModel.getLoading().observe(this, isLoading -> {
            loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                errorTextView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
