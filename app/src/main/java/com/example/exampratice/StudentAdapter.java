package com.example.exampratice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<StudentModel> students;

    public StudentAdapter(List<StudentModel> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_student_item, parent, false);  // your item xml name
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel student = students.get(position);
        holder.txtStudId.setText("ID: " + student.getId());
        holder.txtStudName.setText(student.getName());
        holder.txtStudAge.setText("Age: " + student.getAge());
        holder.txtStudFaculty.setText("Faculty: " + (student.getFaculty() != null ? student.getFaculty() : "N/A"));
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStudId, txtStudName, txtStudAge, txtStudFaculty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStudId = itemView.findViewById(R.id.txtStudId);
            txtStudName = itemView.findViewById(R.id.txtStudName);
            txtStudAge = itemView.findViewById(R.id.txtStudAge);
            txtStudFaculty = itemView.findViewById(R.id.txtStudFaculty);
        }
    }
}