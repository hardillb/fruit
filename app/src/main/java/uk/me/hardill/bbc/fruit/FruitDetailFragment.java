package uk.me.hardill.bbc.fruit;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.me.hardill.bbc.fruit.data.Fruit;
import uk.me.hardill.bbc.fruit.data.FruitBasket;
import uk.me.hardill.bbc.fruit.logging.Logger;

/**
 * A fragment representing a single Fruit detail screen.
 * This fragment is either contained in a {@link FruitListActivity}
 * in two-pane mode (on tablets) or a {@link FruitDetailActivity}
 * on handsets.
 */
public class FruitDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Fruit mItem;

    private Logger logger;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FruitDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logger = Logger.init(getContext());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Log.i("Details", FruitBasket.getTypes().toString());
            mItem = FruitBasket.getDetails(getArguments().getString(ARG_ITEM_ID));
            Log.i("Details",getArguments().getString(ARG_ITEM_ID));
            Log.i("Details", "" + mItem);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getType());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fruit_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.fruit_detail)).setText("Price: £" + String.format("%.2f", mItem.getPrice()/100.0)
                    + "\nWeight: " + String.format("%.2f", mItem.getWeight()/1000.0)  +"Kg");
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.endDisplay();
    }
}
