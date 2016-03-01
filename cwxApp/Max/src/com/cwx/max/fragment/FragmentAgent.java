package com.cwx.max.fragment;

public class FragmentAgent {
	public static void clearFragments() {
		CollectionFragment.containerView = null;
		FamousFragment.containerView = null;
		SearchFragment.containerView = null;
		RankFragment.containerView = null;
	}
}
