package com.example.animationtest

import android.animation.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.animation.addListener
import com.example.animationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val  LOG_TAG = "LT_MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //doing animations non targeted (valueAnimator) and targeted (object animator)
        //either  by attr and by grouping attrs

        //so value animator is just basically a Math transformation     R :  t - > y  i.e  y = f(t)  varying on time
        // when using interpolators  I it is a mappin y = f(x) so we can apply to t these transformations
        //  so vlaue animator is  R:  t ->  y = In( ...I2(I1(t)))

        val tr1: ValueAnimator = ValueAnimator.ofFloat(0f,500f).apply {
            duration = 700
            // this is equal to addListener(object:Animator.AnimatorUpdateListener(){ fun onAnimationUpdate()......
            // depending on the attr you might need to call invalidate()
            addUpdateListener { updatedAnimation ->
                with(binding.tvValueAnimatorBasic1){
                    val value = (updatedAnimation.animatedValue as Float)
                    translationX = value
                    alpha = value * 2f
                }
            }
            addListener(object:Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationStart")
                    (binding.tvValueAnimatorBasic1).setTextColor(Color.RED)
                }

                override fun onAnimationEnd(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationEnd")
                    (binding.tvValueAnimatorBasic1).setTextColor(Color.GREEN)
                }

                override fun onAnimationCancel(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationCancel")
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationRepeat")
                }

            })

        }

        val tr2:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvValueAnimatorBasic2,"translationX",0f,800f).apply {
            duration = 1000
        }

        val tr3:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvObjectAnimator1,"translationX",650f).apply {
            duration = 1000
        }

        /*
        It is called on value animator
        val alp1:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvValueAnimatorBasic1,"alpha", 0f, 1f).apply {
            duration = 1000

        }
        */

        val alp2:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvValueAnimatorBasic2,"alpha", 0f, 1f).apply {
            duration = 1000

        }

        val alp3:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvObjectAnimator1,"alpha", 0f, 1f).apply {
            duration = 1000

        }


        //Individually
/*
        binding.btnStart.setOnClickListener {
            if(!va1.isRunning) va1.start()
            if(!va2.isRunning) va2.start()
            if(!oa1.isRunning) oa1.start()
        }
*/
        // we can use  Animator.AnimatorListener implementation  and  implement all the methods
        tr2.addListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
                Log.d(LOG_TAG, "onAnimationStart")
                (((p0 as? ObjectAnimator)?.target) as? TextView)?.setTextColor(Color.RED)
            }

            override fun onAnimationEnd(p0: Animator?) {
                Log.d(LOG_TAG, "onAnimationEnd")
                (((p0 as? ObjectAnimator)?.target) as? TextView)?.setTextColor(Color.GREEN)
            }

            override fun onAnimationCancel(p0: Animator?) {
                Log.d(LOG_TAG, "onAnimationCancel")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                Log.d(LOG_TAG, "onAnimationRepeat")
            }

        })
        //or we can use an adapter that already have empty overriden methods to just overrid the methods we want
        tr3.addListener (
            object : AnimatorListenerAdapter() {
                override fun onAnimationStart(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationStart")
                    (((p0 as? ObjectAnimator)?.target) as? TextView)?.setTextColor(Color.RED)
                }

                override fun onAnimationEnd(p0: Animator?) {
                    Log.d(LOG_TAG, "onAnimationEnd")
                    (((p0 as? ObjectAnimator)?.target) as? TextView)?.setTextColor(Color.GREEN)
                }

            }
        )


        //As a set
        val animatorSet: AnimatorSet = AnimatorSet().apply {
            play(tr1)
            play(tr2).with(alp2).after(tr1)
            play(tr3).with(alp3).after(tr2)
        }

        with(binding){
            btnStart.setOnClickListener { if(!animatorSet.isRunning) animatorSet.start() }
            btnCancel.setOnClickListener { animatorSet.cancel() }
        }

    }
}