/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension;

import io.spring.initializr.metadata.Dependency;
import io.spring.initializr.web.project.WebProjectRequest;
import org.junit.Test;

/**
 * Tests for {@link SpringSessionBuildCustomizer}.
 *
 * @author Stephane Nicoll
 */
public class SpringSessionBuildCustomizerTests extends AbstractExtensionTests {

	private static final Dependency REDIS = Dependency.withId("session-data-redis",
			"org.springframework.session", "spring-session-data-redis");

	private static final Dependency JDBC = Dependency.withId("session-jdbc",
			"org.springframework.session", "spring-session-jdbc");

	@Test
	public void sessionWithSpringBoot15() {
		WebProjectRequest request = createProjectRequest("session");
		request.setBootVersion("1.5.4.RELEASE");
		generateMavenPom(request)
				.hasDependency("org.springframework.session", "spring-session")
				.hasSpringBootStarterRootDependency().hasSpringBootStarterTest()
				.hasDependenciesCount(3);
	}

	@Test
	public void sessionWithRedisAndSpringBoot15() {
		WebProjectRequest request = createProjectRequest("session", "data-redis");
		request.setBootVersion("1.5.4.RELEASE");
		generateMavenPom(request)
				.hasDependency("org.springframework.session", "spring-session")
				.hasSpringBootStarterDependency("data-redis").hasSpringBootStarterTest()
				.hasDependenciesCount(3);
	}

	@Test
	public void sessionWithJdbcAndSpringBoot15() {
		WebProjectRequest request = createProjectRequest("session", "jdbc");
		request.setBootVersion("1.5.4.RELEASE");
		generateMavenPom(request)
				.hasDependency("org.springframework.session", "spring-session")
				.hasSpringBootStarterDependency("jdbc").hasSpringBootStarterTest()
				.hasDependenciesCount(3);
	}

	@Test
	public void sessionWithSpringBoot20M2() {
		WebProjectRequest request = createProjectRequest("session");
		request.setBootVersion("2.0.0.M2");
		generateMavenPom(request)
				.hasDependency("org.springframework.session", "spring-session")
				.hasSpringBootStarterRootDependency().hasSpringBootStarterTest()
				.hasDependenciesCount(3);
	}

	@Test
	public void noSessionWithRedis() {
		WebProjectRequest request = createProjectRequest("data-redis");
		request.setBootVersion("2.0.0.M3");
		generateMavenPom(request).hasSpringBootStarterDependency("data-redis")
				.hasSpringBootStarterTest().hasDependenciesCount(2);
	}

	@Test
	public void sessionWithNoStore() {
		WebProjectRequest request = createProjectRequest("session", "data-jpa");
		request.setBootVersion("2.0.0.M3");
		generateMavenPom(request)
				.hasDependency("org.springframework.session", "spring-session-core")
				.hasSpringBootStarterDependency("data-jpa").hasSpringBootStarterTest()
				.hasDependenciesCount(3);
	}

	@Test
	public void sessionWithRedis() {
		WebProjectRequest request = createProjectRequest("session", "data-redis");
		request.setBootVersion("2.0.0.M3");
		generateMavenPom(request).hasSpringBootStarterDependency("data-redis")
				.hasSpringBootStarterTest().hasDependency(REDIS).hasDependenciesCount(3);
	}

	@Test
	public void sessionWithRedisReactive() {
		WebProjectRequest request = createProjectRequest("session", "data-redis-reactive");
		request.setBootVersion("2.0.0.M7");
		generateMavenPom(request).hasSpringBootStarterDependency("data-redis-reactive")
				.hasSpringBootStarterTest().hasDependency(REDIS).hasDependenciesCount(3);
	}

	@Test
	public void sessionWithJdbc() {
		WebProjectRequest request = createProjectRequest("session", "jdbc");
		request.setBootVersion("2.0.0.M3");
		generateMavenPom(request).hasSpringBootStarterDependency("jdbc")
				.hasSpringBootStarterTest().hasDependency(JDBC).hasDependenciesCount(3);
	}

	@Test
	public void sessionWithRedisAndJdbc() {
		WebProjectRequest request = createProjectRequest("session", "data-redis", "jdbc");
		request.setBootVersion("2.0.0.M3");
		generateMavenPom(request).hasSpringBootStarterDependency("data-redis")
				.hasSpringBootStarterDependency("jdbc").hasSpringBootStarterTest()
				.hasDependency(REDIS).hasDependency(JDBC).hasDependenciesCount(5);
	}

}