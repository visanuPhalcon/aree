package com.aree.restaurant.app.model.Singleton;

import com.aree.restaurant.app.model.MenuModel;

import java.util.HashMap;

/**
 * Created by Admin on 28/9/2560.
 */

public class RewardList {

    public static RewardList rewardModel;
    public HashMap<String,RewardModel> rewardList = new HashMap<>();

    public static RewardList getInstance()
    {
        if (rewardModel == null)
        {
            rewardModel = new RewardList();
        }
        return rewardModel;
    }

    public void clearAllData() {
        rewardModel.getInstance().getRewardList().clear();
    }



    public static RewardList getRewardModel() {
        return rewardModel;
    }

    public static void setRewardModel(RewardList rewardModel) {
        RewardList.rewardModel = rewardModel;
    }

    public HashMap<String, RewardModel> getRewardList() {
        return rewardList;
    }

    public void setRewardList(HashMap<String, RewardModel> rewardList) {
        this.rewardList = rewardList;
    }
}
