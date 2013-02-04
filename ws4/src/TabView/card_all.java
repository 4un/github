package TabView;

import android.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class card_all extends Fragment
{
@Override
   public void onActivityCreated(Bundle savedInstanceState) {
   super.onActivityCreated(savedInstanceState);
   }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState)
{
View view = inflater.inflate(R.layout.simple_list_item_1, container, false);
return view;
}
}
