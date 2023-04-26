package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    String S_No = null;
    String S_Name = null;
    String T_Name = null;
    Connection connect;
    String connectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void StudentLoginPage(View v){
        setContentView(R.layout.student_login);
    }

    public void TeacherLoginPage(View v){
        setContentView(R.layout.teacher_login);
    }
    public void StudentLogin(View v){
        EditText RollNo = (EditText) findViewById(R.id.StudentName);
        String StudentNo = RollNo.getText().toString();
        EditText pass = (EditText) findViewById(R.id.Studentpass);
        String StudentPass = pass.getText().toString();
        S_No = StudentNo;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){
                String query = String.format("select * from student where pass = '%s' and rollNo = %s",StudentPass,StudentNo);
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    setContentView(R.layout.student_page);
                    final TextView sn = (TextView) findViewById(R.id.studentn);
                    S_Name = rs.getString(2);
                    sn.setText(S_Name);

                }
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }

    }
    public void StudentResultToPage(View v){
        setContentView(R.layout.student_page);
        TextView sn = (TextView) findViewById(R.id.studentn);
        sn.setText(S_Name);
    }
    public void StudentInfo(View v){
        setContentView(R.layout.student_info);
        ConnectionHelper connectionHelper = new ConnectionHelper();
        connect = connectionHelper.ConnectionClass();
        try {
        if (connect != null){
            TextView name = (TextView) findViewById(R.id.info_name);
            TextView fname = (TextView) findViewById(R.id.info_fname);
            TextView mname = (TextView) findViewById(R.id.info_mname);
            TextView mobile = (TextView) findViewById(R.id.info_mobile);
            TextView ads = (TextView) findViewById(R.id.info_social);
            String query = String.format("select * from info i INNER JOIN student s ON  i.student_rollNo = s.rollNo where i.student_rollNo = '%s'",S_No);
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                name.setText(rs.getString(9) + " " + rs.getString(10));
                fname.setText(rs.getString(2) + " " + rs.getString(3));
                mname.setText(rs.getString(5) + " " + rs.getString(6));
                mobile.setText(rs.getString(4));
                ads.setText(rs.getString(7));
            }
            connect.close();
        }
        else {
            connectionResult = "check connection";
        }
    }catch (Exception ex){
        Log.e("error",ex.getMessage());
    }


    }
    public void UpdateInfo(View v){
        setContentView(R.layout.student_update);
        ConnectionHelper connectionHelper = new ConnectionHelper();
        connect = connectionHelper.ConnectionClass();
        try {
            if (connect != null){
                TextView name = (TextView) findViewById(R.id.update_name);
                TextView fname = (TextView) findViewById(R.id.update_fname);
                TextView mname = (TextView) findViewById(R.id.update_mname);
                String query2 = String.format("select * from info i INNER JOIN student s ON i.student_rollNo = s.rollNo where i.student_rollNo = '%s'",S_No);
                Statement st = connect.createStatement();
                ResultSet rs2 = st.executeQuery(query2);
                while (rs2.next()){

                    name.setText(rs2.getString(9) + " " + rs2.getString(10));
                    fname.setText(rs2.getString(2) + " " + rs2.getString(3));
                    mname.setText(rs2.getString(5) + " " + rs2.getString(6));

                }
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }

    }
    public void updated(View v){
        ConnectionHelper connectionHelper = new ConnectionHelper();
        connect = connectionHelper.ConnectionClass();
        try {
            if (connect != null){
                EditText mobile = (EditText) findViewById(R.id.update_mobile);
                EditText adress = (EditText) findViewById(R.id.update_social);
                String mob = mobile.getText().toString();
                String ads = adress.getText().toString();
                String query2 = String.format("update info SET mobile = '%s' , adress = '%s' where student_rollNo = '%s'",mob,ads,S_No);
                Statement st = connect.createStatement();
                if (!mob.equals("") && !ads.equals("")){
                    st.executeUpdate(query2);
                }
                setContentView(R.layout.student_info);
                TextView name = (TextView) findViewById(R.id.info_name);
                TextView fname = (TextView) findViewById(R.id.info_fname);
                TextView mname = (TextView) findViewById(R.id.info_mname);
                TextView mobilet = (TextView) findViewById(R.id.info_mobile);
                TextView adst = (TextView) findViewById(R.id.info_social);
                String query = String.format("select * from info i INNER JOIN student s ON  i.student_rollNo = s.rollNo where i.student_rollNo = '%s'",S_No);
                Statement st2 = connect.createStatement();
                ResultSet rs = st2.executeQuery(query);
                while (rs.next()){
                    name.setText(rs.getString(9) + " " + rs.getString(10));
                    fname.setText(rs.getString(2) + " " + rs.getString(3));
                    mname.setText(rs.getString(5) + " " + rs.getString(6));
                    mobilet.setText(rs.getString(4));
                    adst.setText(rs.getString(7));
                }
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }
    }
    public void PageToHme(View v){
        setContentView(R.layout.activity_main);
        S_No = null;
        S_Name = null;
        T_Name =null;
    }

    public void TeacherResultToPage(View v){
        setContentView(R.layout.student_page);
        TextView sn = (TextView) findViewById(R.id.studentn);
        sn.setText(S_Name);
    }
    public void TeacherLogin(View v){
        EditText Name = (EditText) findViewById(R.id.TeacherName);
        String TeacherName = Name.getText().toString();
        EditText pass = (EditText) findViewById(R.id.Teacherpass);
        String TeacherPass = pass.getText().toString();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){
                String query = String.format("select * from teacher where t_mobile = '%s' and t_fname = '%s'",TeacherPass,TeacherName);
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    setContentView(R.layout.teacher_page);
                    T_Name = rs.getString(2);
                    TextView tn = (TextView) findViewById(R.id.textViewTn);
                    tn.setText(T_Name);


                }
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }
    }
    public void StudentResult(View v){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){
                setContentView(R.layout.student_result);
                TextView name = (TextView) findViewById(R.id.result_name);
                TextView maths = (TextView) findViewById(R.id.marks_math);
                TextView eng = (TextView) findViewById(R.id.marks_eng);
                TextView hindi = (TextView) findViewById(R.id.marks_hindi);
                TextView science = (TextView) findViewById(R.id.marks_science);
                TextView social = (TextView) findViewById(R.id.marks_social);
                String query = String.format("select * from student s INNER JOIN results r ON  s.rollNo = r.student_rollNo where s.rollNo = '%s'",S_No);
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    String fullname = rs.getString(2)+ " " + rs.getString(3);
                    name.setText(fullname);
                    maths.setText(rs.getString(6));
                    eng.setText(rs.getString(7));
                    hindi.setText(rs.getString(8));
                    science.setText(rs.getString(9));
                    social.setText(rs.getString(10));
                }
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }

    }
    public void Registerpage(View v){
        setContentView(R.layout.register_student);
    }
    public void Register(View v){

        EditText registerno = (EditText) findViewById(R.id.register_no);
        EditText registerfname = (EditText) findViewById(R.id.register_fname);
        EditText registerlname = (EditText) findViewById(R.id.register_lname);
        EditText registerffname = (EditText) findViewById(R.id.register_ffname);
        EditText registerflname = (EditText) findViewById(R.id.register_flname);
        String RollNo = registerno.getText().toString();
        String Sname = registerfname.getText().toString();
        String Slname = registerlname.getText().toString();
        String ffname = registerffname.getText().toString();
        String flname = registerflname.getText().toString();
        S_No = RollNo;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){
                String query3 = String.format("insert into results values(%s,'','','','','')",S_No);
                String query2 = String.format("insert into student values(%s,'%s','%s','')",RollNo,Sname,Slname);
                String query1 = String.format("insert into info values(%s,'%s','%s','','','','')",RollNo,ffname,flname);
                Statement st = connect.createStatement();
                st.executeUpdate(query1);
                st.executeUpdate(query2);
                setContentView(R.layout.register_more);
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }

    }
    public void Registermore(View v){

        EditText registermfname = (EditText) findViewById(R.id.register_mfname);
        EditText registermlname = (EditText) findViewById(R.id.register_mlname);
        EditText registermobile = (EditText) findViewById(R.id.register_mobile);
        EditText registerads = (EditText) findViewById(R.id.register_ads);
        String mfname = registermfname.getText().toString();
        String mlname = registermlname.getText().toString();
        String mobile = registermobile.getText().toString();
        String address = registerads.getText().toString();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){
                String query2 = String.format("update student set pass = '%s' where rollNo = '%s'",mobile,S_No);
                String query1 = String.format("update info set mobile = '%s', mfname ='%s', mlname = '%s',adress = '%s' where student_rollNo = '%s'",mobile,mfname,mlname,address,S_No);
                Statement st = connect.createStatement();
                st.executeUpdate(query1);
                st.executeUpdate(query2);
                setContentView(R.layout.teacher_page);
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }
    }
    public void UpdateResultPage(View v){
        setContentView(R.layout.result_input);
    }
    public void UpdateResult(View v){
        EditText resultno = (EditText) findViewById(R.id.result_no);
        EditText resultmath = (EditText) findViewById(R.id.result_math);
        EditText resulteng = (EditText) findViewById(R.id.result_english);
        EditText resulthindi = (EditText) findViewById(R.id.result_hindi);
        EditText resultsci = (EditText) findViewById(R.id.result_science);
        EditText resultsocial = (EditText) findViewById(R.id.result_social);

        String rollNo = resultno.getText().toString();
        String math = resultmath.getText().toString();
        String eng = resulteng.getText().toString();
        String hindi = resulthindi.getText().toString();
        String science = resultsci.getText().toString();
        String social = resultsocial.getText().toString();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null){

                String query1 = String.format("update results set Mathematics = '%s', English ='%s', Hindi = '%s',Science = '%s',Social = '%s' where student_rollNo = '%s'",math,eng,hindi,science,social,rollNo);
                Statement st = connect.createStatement();
                st.executeUpdate(query1);
                setContentView(R.layout.teacher_page);
                TextView tn = (TextView) findViewById(R.id.textViewTn);
                tn.setText(T_Name);
                connect.close();
            }
            else {
                connectionResult = "check connection";
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }
    }
}