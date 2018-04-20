package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 17/9/2560.
 */

public class GetRewardResposeModel extends CommonResponseModel {

    @SerializedName("reward")
    ArrayList<RewardModel> reward = new ArrayList<RewardModel>();

    public ArrayList<RewardModel> getReward() {
        return reward;
    }

    public void setReward(ArrayList<RewardModel> reward) {
        this.reward = reward;
    }
}
