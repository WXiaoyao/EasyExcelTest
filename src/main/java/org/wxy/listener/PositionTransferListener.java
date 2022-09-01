package org.wxy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.wxy.common.UserMap;
import org.wxy.model.CommonModel;
import org.wxy.model.PersonAndRole;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PositionTransferListener implements ReadListener<CommonModel> {

    private Set<String> positionTransferSet = new HashSet<>();

    @Override
    public void invoke(CommonModel commonModel, AnalysisContext context) {
        positionTransferSet = UserMap.getPositionTransferSet();
        String account = commonModel.getAccount();
        positionTransferSet.add(account);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("异动人员名单清点完毕..");
    }
}
