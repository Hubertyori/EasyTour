package studio.opencloud.easytour21.orders;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import studio.opencloud.easytour21.area_choose.JsonFileReader;
import studio.opencloud.easytour21.area_choose.provincebean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.Login_Interface;
import studio.opencloud.easytour21.internet.interfaces.SendOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.users.Login;
import studio.opencloud.easytour21.users.UserInformation;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendOrder extends Activity {
    private Button btnSend;
    private EditText etLocation;
    private EditText etDate;
    private EditText etPeopleNum;
    private EditText etDescription;
    private TextView tvWithDraw;
    private String Date = "";
    //  省份
    ArrayList<provincebean> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();
    Calendar ca = Calendar.getInstance();
    int  mYear = ca.get(Calendar.YEAR);
    int  mMonth = ca.get(Calendar.MONTH);
    int  mDay = ca.get(Calendar.DAY_OF_MONTH);

    private Intent intent;
    private UserInformationData userData;

    private String location;
    private String date;
    private int peopleNum;
    private String description;
    private String phone;
    TimePickerView timeOptions;
    OptionsPickerView pvOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);
        init();
//        Intent intent = getIntent();
//        phone = intent.getStringExtra("userPhone");
        intent = getIntent();
        userData = intent.getParcelableExtra("userData");
        //默认使用测试用号码：15523352924
        phone = userData.getTelephone();
        pvOptions = new OptionsPickerView(this);

        String province_data_json = JsonFileReader.getJson(this, "province.json");
        //  解析json数据
        parseJson(province_data_json);

        //  设置三级联动效果
        pvOptions.setPicker(provinceBeanList, cityList, districtList, true);



        //  设置选择的三级单位
        //pvOptions.setLabels("省", "市", "区");
        //pvOptions.setTitle("选择城市");

        //  设置是否循环滚动
        pvOptions.setCyclic(false, false, false);


        // 设置默认选中的三级项目
        pvOptions.setSelectOptions(0, 0, 0);
        //  监听确定选择按钮
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String city = provinceBeanList.get(options1).getPickerViewText();
                String address;
                //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + districtList.get(options1).get(option2).get(options3);
                } else {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + cityList.get(options1).get(option2)
                            + " " + districtList.get(options1).get(option2).get(options3);
                }
                etLocation.setText(address);



            }
        });


        final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
        {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                String days;
                if (mMonth + 1 < 10) {
                    if (mDay < 10) {
                        days = new StringBuffer().append(mYear).append("年").append("0").
                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    } else {
                        days = new StringBuffer().append(mYear).append("年").append("0").
                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    }

                } else {
                    if (mDay < 10) {
                        days = new StringBuffer().append(mYear).append("年").
                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    } else {
                        days = new StringBuffer().append(mYear).append("年").
                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    }

                }
                etDate.setText(days);

            }
        };
        etLocation.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                pvOptions.show();
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 调用时间选择器
                new DatePickerDialog(SendOrder.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });

    }

    private void init() {
        btnSend = findViewById(R.id.btn_send_order_send);
        etDate = findViewById(R.id.et_send_order_date);
        etDescription = findViewById(R.id.et_send_order_description);
        etLocation = findViewById(R.id.et_send_order_location);
        etPeopleNum = findViewById(R.id.et_send_order_people_num);
        tvWithDraw = findViewById(R.id.tv_send_order_withdraw);
        btnSend.setOnClickListener(MyListener);
        tvWithDraw.setOnClickListener(MyListener);
    }


    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_send_order_send:
                    send();
                    break;
                case R.id.tv_send_order_withdraw:
                    finish();
                    break;
            }
        }
    };

    //  解析json填充集合
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new provincebean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void send() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        SendOrder_Interface request = retrofit.create(SendOrder_Interface.class);
        //对 发送请求 进行封装(设置需要翻译的内容)
        collectInfo();
        Call<Register_Translation> call = request.signUpInformation(phone,location,Date,peopleNum,description);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(SendOrder.this, "请求成功", Toast.LENGTH_SHORT).show();
                System.out.println("********************************************************");
                System.out.println(response.body().getMessage());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                Toast.makeText(SendOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void collectInfo() {
        peopleNum = Integer.parseInt(etPeopleNum.getText().toString());
        location = etLocation.getText().toString();
        description = etDescription.getText().toString();
        date = etDate.getText().toString();
        String[] s=date.split("\\D+");
        //打印分割的!
        Date = s[0]+"-"+s[1]+"-"+s[2];
    }
}
