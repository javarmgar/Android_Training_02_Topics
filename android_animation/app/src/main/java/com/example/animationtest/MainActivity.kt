package com.example.animationtest

import android.animation.*
import android.graphics.Color
import android.graphics.Interpolator
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.*
import android.widget.TextView
import androidx.constraintlayout.motion.widget.KeyFrames
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
        val durationTr1 = 3000L
        val tr1: ValueAnimator = ValueAnimator.ofFloat(0f,500f).apply {
            duration = durationTr1
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
            interpolator = TimeInterpolator { x ->
                (((x + 1f)*(x + 1f)) - 1)/3
            }
        }

        val tr2:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvValueAnimatorBasic2,"translationX",0f,800f).apply {
            duration = 1000
            interpolator = AnticipateOvershootInterpolator()//AccelerateInterpolator()
        }

        val tr3:ObjectAnimator = ObjectAnimator.ofFloat(binding.tvObjectAnimator1,"translationX",0f,650f).apply {
            duration = 3000
            interpolator = CycleInterpolator(3f)
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


        //animation with keyFrames

        /*
        A key frame object consists  of a time/value that allows define a specific state
        at a specific time in an animation

        Keyframe_i  -> (has own )   Interpolator_i

         */

        val kf1 = Keyframe.ofFloat(0f, 0f)
        val kf2 = Keyframe.ofFloat(0.5f, 360f)
        val kf3 = Keyframe.ofFloat(1f, 0f)

        val phvRotation = PropertyValuesHolder.ofKeyframe("rotation", kf1, kf2, kf3)
        ObjectAnimator.ofPropertyValuesHolder(binding.btnAnimatorKeyFrame, phvRotation).apply {
            duration = 5000
            binding.btnAnimatorKeyFrame.setOnClickListener {
                if(!this.isRunning) this.start()
            }
        }


        //we can use propertyvalues holder to apply many attr animation into the same object animator

        //example with muktiple animaotr objects

        /*
        val animX = ObjectAnimator.ofFloat(myView, "x", 50f)
        val animY = ObjectAnimator.ofFloat(myView, "y", 100f)
        AnimatorSet().apply {
            playTogether(animX, animY)
            start()
        }
         */

        //example in one Object animator using propertyValuesHolder
        ObjectAnimator.ofPropertyValuesHolder(
            binding.btnCancel,
            PropertyValuesHolder.ofFloat("x",500f),
            PropertyValuesHolder.ofFloat("y",50f)

        ).apply {
            binding.btnCancel.setOnClickListener{if(!this.isRunning) this.start()}
        }


        //there's another class tool called  ViewPropertyAnimator
        // views have a method called animate() this method have access to attr properties
        // they are called as methods an set by caliing those methods


        binding.btnViewPropertyAnimator.setOnClickListener { view  ->
            with(view.animate()){
                duration = 3000
                alpha(0f)
            }
        }

        binding.ivAnimatedVector.setOnClickListener {
            it.setBackgroundResource(R.drawable.animated_vector_drawble)
            (it.background as AnimatedVectorDrawable).apply {
                if(!this.isRunning) this.start()
            }
        }
    }
}