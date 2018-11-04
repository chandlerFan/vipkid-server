package com.quxueyuan.server.api.service.UserJoinService;

import com.quxueyuan.bean.vo.req.User2TestCommitParam;
import org.springframework.http.ResponseEntity;

public interface User2CommitChoiseServer {

	ResponseEntity userChoiseQuestion(User2TestCommitParam user2CommitChoiseParam) throws Exception;
}
