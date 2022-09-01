package org.wxy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.wxy.common.UserMap;
import org.wxy.model.CommonModel;

import java.util.Set;

@Slf4j
public class ServingStaffListener implements ReadListener<CommonModel> {

    private Set<String> servingStaffAccountSet;

    @Override
    public void invoke(CommonModel commonModel, AnalysisContext context) {
        servingStaffAccountSet = UserMap.getServingStaffAccountSet();
        String servingAccount = commonModel.getAccount();
        servingStaffAccountSet.add(servingAccount);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("在职人员名单清点完毕..");
    }
}
