package cs.smu.ac.embeddedsoftware

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.isg_join_user.*
import kotlinx.android.synthetic.main.jhy_activity_myinfo.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.reflect.typeOf


class ISGJoinUser : AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("user")

    val data : UserData? = UserData("ImSangKyun", "isg1031@naver.com", "abcd1234!", 201511051, "010-4741-7383")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isg_join_user)

        confirmEmailButton.setOnClickListener {
            val emailString: String = editJoinId.text.toString()
            val eDataValue = myRef.child(emailString).addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onCancelled(p0: DatabaseError){

                }
                override fun onDataChange(p0: DataSnapshot){
                    val p2 = p0.getValue() as Map<String, UserData>
                    val testString = p2.get("isg1031")

                    if(testString != null)
                        testerTextView.text = testString.email
                    if(p2.toString() != "null"){
                        //testerTextView.text = "true"
                    }
                    else{
                        //testerTextView.text = "false"
                    }
                    /*
                    for(snapshot in p0.children){
                        if(snapshot.key.equals("emailString"))
                            //testerTextView.text = snapshot.value.toString()
                        else
                            //testerTextView.text = "ERROR"
                    }

                    */

                }
            })
            val childUpdates = HashMap<String, Any>()

            testerTextView.text = eDataValue.toString()
        }

        cancelAccount.setOnClickListener {
            finish()
        }

        createAccount.setOnClickListener{

        }


    }
}
