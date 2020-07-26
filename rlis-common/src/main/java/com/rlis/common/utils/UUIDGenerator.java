package com.rlis.common.utils;

import java.util.UUID;

/**
 * @description: UUID主键生成器
 * @date: 2020/7/25 0025 20:19
 */
public class UUIDGenerator {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
