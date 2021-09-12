package andersu.dev.isleeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.ERROR
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var selectedOption: View? = null;
    private val viewsBtn: List<Int> = listOf(R.id.pone_view, R.id.ptwo_view, R.id.pthree_view);
    private var activeId: Int? = null;
    private lateinit var applyBtn: Button;
    private lateinit var cancelBtn: Button;

    val notificationManager: NotificationManager = NotificationManager();
    lateinit var storage: ProfileStorage;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storage = ProfileStorage(this);
        applyBtn = findViewById(R.id.applybtn);
        cancelBtn = findViewById(R.id.cancelbtn);

        applyBtn.visibility = View.GONE;
        activeId = storage.read();
        
        applyBtn.setOnClickListener {
            if(activeId != null) {
                storage.save(activeId!!);
            }
        }
        notificationManager.createChannel(this);

        viewsBtn.forEach { id ->
            val el: View = findViewById(id);
            el.setBackgroundColor(resources.getColor(R.color.gray))
            val index = viewsBtn.indexOf(id);

            if(activeId != null && activeId == index) {
                selectedOption = el;
                selectedOption?.setBackgroundColor(resources.getColor(R.color.purple_500))
                return;
            }

            el.setOnClickListener {

                if(activeId != null){
                    return@setOnClickListener
                }

                if(selectedOption != null) {

                    if(it == selectedOption) {
                        selectedOption?.setBackgroundColor(resources.getColor(R.color.gray));
                        selectedOption = null;
                        updateApplyButton()
                    }
                }

                selectedOption = el;
                activeId = index;
                selectedOption?.setBackgroundColor(resources.getColor(R.color.purple_500))
                updateApplyButton()
            }
        }
    }

    private fun updateApplyButton() {
        if (selectedOption != null && activeId != viewsBtn.indexOf(selectedOption?.id)) {
            applyBtn.visibility = View.VISIBLE;
        } else {
            applyBtn.visibility = View.GONE;
        }

        if(activeId != null && activeId == viewsBtn.indexOf(selectedOption?.id)) {
            cancelBtn.visibility = View.VISIBLE;
        } else {
            cancelBtn.visibility = View.GONE;

        }
    }

    private fun index2Minutes(index: Int): Int  {
        return when (index) {
            0 -> {
                20;
            }
            1 -> {
                40
            }
            else -> {
                60
            }
        }
    }

}