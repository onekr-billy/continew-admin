/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseDO;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.system.enums.LogoutModeEnum;
import top.continew.admin.system.enums.ReplacedRangeEnum;

import java.io.Serial;
import java.util.List;

/**
 * 客户端实体
 *
 * @author KAI
 * @author Charles7c
 * @since 2024/12/03 16:04
 */
@Data
@TableName(value = "sys_client", autoResultMap = true)
public class ClientDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 登录类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authType;

    /**
     * Token 最低活跃频率（单位：秒，-1：不限制，永不冻结）
     */
    private Long activeTimeout;

    /**
     * Token 有效期（单位：秒，-1：永不过期）
     */
    private Long timeout;

    /**
     * 是否允许同一账号多地同时登录 （为 true 时允许一起登录, 为 false 时新登录挤掉旧登录）
     */
    private Boolean isConcurrent;

    /**
     * 同一账号最大登录数量，-1代表不限 （只有在 isConcurrent=true, isShare=false 时此配置项才有意义）
     */
    private int maxLoginCount;

    /**
     * 当 isConcurrent=false 时，顶人下线的范围 (CURR_DEVICE_TYPE=当前指定的设备类型端, ALL_DEVICE_TYPE=所有设备类型端)
     */
    private ReplacedRangeEnum replacedRange;

    /**
     * 溢出 maxLoginCount 的客户端，将以何种方式注销下线 (LOGOUT=注销下线, KICKOUT=踢人下线, REPLACED=顶人下线)
     */
    private LogoutModeEnum overflowLogoutMode;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}