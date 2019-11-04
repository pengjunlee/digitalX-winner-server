package com.pengjunlee.listener;

import com.pengjunlee.utils.TmallUtil;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author pengjunlee
 * @create 2019-09-23 14:57
 */
public class EliminateCommentListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        TmallUtil.loadAllEliminateComments();
    }
}
