package com.aree.restaurant.app.fragment.Admin;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.FragmentSetTimeBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHolidayRequsetModel;
import com.aree.restaurant.app.model.RequsetModel.admin.SetHolidayRequsetModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHolidayResponseModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimeFragment extends Fragment  {

    static SetTimeFragment fragment;
    FragmentSetTimeBinding binding;
    DateFormat df;
    Date startDate;
    Date endDate;
    Calendar startCalendar;
    Calendar endCalendar;
    Calendar cal;
    String dateString;
    int size;
    Event ev1 ;
    ArrayList<Event> holiday = new ArrayList<Event>();
    GetHolidayRequsetModel requestModel = new GetHolidayRequsetModel();
    GetHolidayResponseModel responseModel = new GetHolidayResponseModel();
    private Restful restful = new Restful();



    public void refreshView()
    {
        if(holiday!=null)
            holiday.clear();
        if(responseModel.getHoliday()!=null)
            responseModel.getHoliday().clear();
        Log.e("jay", "refreshView: " );
        restful.createService(getActivity() , requestModel  , callback2);
    }


//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        Log.e("jay", "onResume: " );
//
//        if(holiday!=null)
//            holiday.clear();
//        if(responseModel.getHoliday()!=null)
//            responseModel.getHoliday().clear();
//
//        new Restful<>().createService(getActivity() , requestModel  , callback2);
//
//
//    }

    public static SetTimeFragment newInstance() {
        Bundle args = new Bundle();
        fragment = new SetTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SetTimeFragment getInstance() {
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_set_time, container, false);
        View rootview = binding.getRoot();
        init();
        return rootview;
    }


    public void init()
    {
//        binding.calendar.setLocale(TimeZone.getDefault(),new Locale("th"));

        binding.calendar.setHovered(true);
        binding.calendar.setShouldDrawDaysHeader(true);
        binding.calendar.shouldDrawIndicatorsBelowSelectedDays(true);
        cal = Calendar.getInstance( );
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+543);
        df = new SimpleDateFormat("MMMM yyyy",new Locale("th", "TH"));
        dateString = df.format(cal.getTime() );


        binding.calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        binding.tvDate.setText(dateString);
        binding.calendar.setListener(new CompactCalendarView.CompactCalendarViewListener()
        {
            @Override
            public void onDayClick(Date date)
            {


                cal.setTimeInMillis(date.getTime());
                df = new SimpleDateFormat("yyyy-MM-dd");
                dateString = df.format(cal.getTime());
                size = binding.calendar.getEvents(date).size();

                if(size==0)
                {
                    ev1 = new Event(ContextCompat.getColor(getActivity(),R.color.colorOrange), cal.getTimeInMillis(), "");
                    binding.calendar.addEvent(ev1);
                    holiday.add(ev1);
                }
                else
                {

                    binding.calendar.removeEvents(date);
                    int length = holiday.size();
                    for(int z = 0 ; z<length ; z++)
                    {

                        if(holiday.get(z).getTimeInMillis()== cal.getTimeInMillis() )
                        {
                            holiday.remove(z);
                            break;
                        }

                    }

                }



//                Log.e("jay", "onDayClick: "+binding.calendar.getEventsForMonth(date) );
//                Log.e("jay", "dateString: "+dateString );
//                Log.e("jay", "DAY_OF_WEEK: "+cal.get(Calendar.DAY_OF_WEEK) );
//                Log.e("jay", "DAY_OF_MONTH: "+cal.get(Calendar.DAY_OF_MONTH) );
//                Log.e("jay", "MONTH: "+cal.get(Calendar.MONTH)+1 );


            }

            @Override
            public void onMonthScroll(Date date)
            {


                df = new SimpleDateFormat("MMMM yyyy",new Locale("th", "TH"));
                cal.setTimeInMillis(date.getTime());
                cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+543);
                dateString = df.format(cal.getTime());
                binding.tvDate.setText(dateString);


            }
        });


        binding.btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                Utils.showProgressDialog(getActivity(),"กำลังส่งข้อมูล");
                SetHolidayRequsetModel requestModel ;
                requestModel = new SetHolidayRequsetModel();


//                responseModel.getHoliday().clear();
                requestModel.setHoliday(holiday);
                restful.createService(getActivity() , requestModel  , callback);
                Log.e("jay", "get data : " + responseModel.getHoliday().size()   );
                Log.e("jay", "send data : " + holiday.size()   );



//                int length = holiday.size();
//                for(int z = 0 ; z<length ; z++)
//                {
//                        cal.setTimeInMillis( holiday.get(z).getTimeInMillis());
//                        df = new SimpleDateFormat("yyyy-MM-dd");
//                        dateString = df.format(cal.getTime());
//                        Log.e("SetTimeFragment", "dateString: "+dateString );
//                        Log.e("SetTimeFragment", "getData: "+holiday.get(z).getData() );
//                        Log.e("SetTimeFragment", "getTimeInMillis: "+holiday.get(z).getTimeInMillis() );
//                        Log.e("SetTimeFragment", "DAY_OF_MONTH: "+cal.get(Calendar.DAY_OF_MONTH) );
//
//                }
            }
        });


    }



    // send data
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

        }

        @Override
        public void onResponse(Object object)
        {
            Utils.dismissDialog();

//            responseModel = (GetMenuResponseModel) object ;
//            initRecycleView();
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }

        @Override
        public void onFailedConnection() {
            getActivity().finish();

        }


    };




    // get data
    OnResponseFromServer callback2 = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object)
        {

            binding.calendar.removeAllEvents();
            responseModel = (GetHolidayResponseModel) object ;
            int size = responseModel.getHoliday().size();
            for (int i =  0 ; i<size ; i++)
            {
                Log.e("OnResponseFromServer", "getTimeInMillis: "+responseModel.getHoliday().get(i).getTimeInMillis() );
                binding.calendar.addEvent( new Event(ContextCompat.getColor(getActivity(),R.color.colorOrange)
                        , responseModel.getHoliday().get(i).getTimeInMillis() , "") );

                holiday.add( new Event(ContextCompat.getColor(getActivity(),R.color.colorOrange)
                        , responseModel.getHoliday().get(i).getTimeInMillis(), ""));
            }


//            if(responseModel.getHoliday().size()>0)
//            {
//                size = responseModel.getHoliday().size();
//                Event ev2;
//                for(int i = 0 ; i < size ; i++)
//                {
//                    ev2 = new Event(ContextCompat.getColor(getActivity(),R.color.colorOrange)
//                            , responseModel.getHoliday().get(i).getTimeInMillis(), "");
//                    holiday.add(ev2);
//
//
//                }
//            }

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }

        @Override
        public void onFailedConnection() {
            getActivity().finish();

        }


    };


}
