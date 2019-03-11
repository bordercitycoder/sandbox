package com.example.code_samples.member_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.code_samples.R;
import com.example.code_samples.model.Member;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    private final List<Member> data = new ArrayList<>();

    MemberListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner) {

        viewModel.getMembers().observe(lifecycleOwner, members -> {
            data.clear();
            if (members != null) {
                data.addAll(members);
            }
            notifyDataSetChanged();
        });

    }

    @NotNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MemberViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static final class MemberViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.firstNameTextView)
        TextView firstNameTextView;
        @BindView(R.id.lastNameTextView)
        TextView lastNameTextView;
        @BindView(R.id.streetAddressTextView)
        TextView streetAddressTextView;

        MemberViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Member member) {
            firstNameTextView.setText(member.first_name);
            lastNameTextView.setText(member.last_name);
            streetAddressTextView.setText(member.street_address);
        }
    }
}
