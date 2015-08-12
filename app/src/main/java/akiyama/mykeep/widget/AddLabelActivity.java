package akiyama.mykeep.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akiyama.mykeep.R;
import akiyama.mykeep.adapter.SearchAdapter;
import akiyama.mykeep.base.BaseObserverActivity;
import akiyama.mykeep.event.EventType;
import akiyama.mykeep.view.SearchLayout;
import akiyama.mykeep.vo.SearchVo;

/**
 * FIXME
 *
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-07-15  17:22
 */
public class AddLabelActivity extends BaseObserverActivity {

    private SearchLayout mSearchSly;
    private TextWatcher mText;
    private SearchAdapter mSearchAdpter;
    private List<SearchVo> mSearchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label);
    }

    @Override
    protected void onChange(String eventType) {
        Toast.makeText(mContext, eventType, Toast.LENGTH_SHORT).show();
        if(eventType.equals(EventType.EVENT_ADD_LABEL_LIST)){
            mSearchList=mSearchAdpter.getSearchVoList();
        }
    }

    @Override
    protected String[] getObserverEventType() {
        return new String[]{
                EventType.EVENT_ADD_LABEL_LIST
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_label,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void findView() {
        mSearchSly = (SearchLayout) findViewById(R.id.search_sly);
    }

    @Override
    protected void initView() {
        setToolBarTitle("增加标签");
        mSearchList=new ArrayList<SearchVo>();
        for(int i=0;i<100;i++){
            if(i%2==0){
                mSearchList.add(new SearchVo("个人",false));
            }else{
                mSearchList.add(new SearchVo("家庭",false));
            }

        }
        mSearchAdpter=new SearchAdapter(mContext,mSearchList);
    }

    @Override
    protected void setOnClick() {
        mText=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    mSearchAdpter.refreshDate(mSearchList);
                }else{
                    mSearchAdpter.refreshDate(queryList(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mSearchSly.setInputChangeListener(mText);
        mSearchSly.setmAdpter(mSearchAdpter);
    }

    @Override
    public void onClick(View v) {

    }

    private List<SearchVo> queryList(String name){
        List<SearchVo> searchs=new ArrayList<SearchVo>();
        for(int i=0;i<mSearchList.size();i++){
            if(mSearchList.get(i).getName().contains(name)){
                searchs.add(mSearchList.get(i));
            }
        }
        return searchs;
    }

}