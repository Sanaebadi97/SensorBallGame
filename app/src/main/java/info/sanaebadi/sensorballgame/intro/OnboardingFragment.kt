package info.sanaebadi.sensorballgame.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import info.sanaebadi.sensorballgame.R
import info.sanaebadi.sensorballgame.databinding.FragmentOnboardingBinding


@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private val viewModel: OnboardingViewModel by viewModels()
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "hey there ^_^ ",
                "Hi, Welcome to my game.\n" +
                        "Learn how to play.",
                "onboardingwelcome.json"
            ),
            IntroSlide(
                "How to play!",
                "Move your phone vertically to the sides with the sensor so that the balls are in a horizontal direction",
                "gameloader.json"
            ),
            IntroSlide(
                "The last point!",
                " Are you ready ?! play and enjoy ^_^",
                "balls.json"
            )
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = introSliderAdapter
        binding.indicator.setViewPager(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == introSliderAdapter.itemCount - 1) {
                        val animation = AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.on_boarding
                        )
                        binding.buttonNext.animation = animation
                        binding.buttonNext.text =requireContext().getString(com.comma.uikit.R.string.finish)
                        binding.buttonNext.setOnClickListener {
                            viewModel.saveOnboarding(true)
                            requireView().findNavController()
                                .navigate(OnboardingFragmentDirections.actionOnboardingFragmentToGameFragment())
                        }
                    } else {
                        binding.buttonNext.text = requireContext().getString(com.comma.uikit.R.string.next)
                        binding.buttonNext.setOnClickListener {
                            binding.viewPager.currentItem.let {
                                binding.viewPager.setCurrentItem(it + 1, false)
                            }
                        }
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}