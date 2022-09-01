package org.wxy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.wxy.common.UserMap;
import org.wxy.model.CommonModel;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class TechnicianCheckListener implements ReadListener<CommonModel> {

    private static Set<String> technicianSet = new HashSet<>();

    @Override
    public void invoke(CommonModel commonModel, AnalysisContext context) {
        technicianSet = UserMap.getTechnicianSet();
        String account = commonModel.getAccount();
        technicianSet.add(account);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("技术人员名单清点完毕..");
    }
}
