package com.ccm.user.user.domain.interfaces;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.entities.MessageQueue;
import com.ccm.user.user.domain.vo.UserId;

public interface SendInterface {

    public void main(MessageQueue messageQueue);
}
