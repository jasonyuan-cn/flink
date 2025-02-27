/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.leaderelection;

import java.util.UUID;

/**
 * {@code AbstractLeaderElectionService} provides a generic implementation of the {@link
 * LeaderElection} handling.
 */
public abstract class AbstractLeaderElectionService implements LeaderElectionService {

    @Override
    public LeaderElection createLeaderElection() {
        return new DefaultLeaderElection(this);
    }

    /**
     * Registers the given {@link LeaderContender} with the underlying {@code
     * LeaderElectionService}. Leadership changes are starting to be reported to the {@code
     * LeaderContender}.
     */
    protected abstract void register(LeaderContender contender) throws Exception;

    /** Removes the passed {@code LeaderContender} from the {@code LeaderElectionService}. */
    protected abstract void remove(LeaderContender contender);

    /** Confirms the leadership with the given session ID and address. */
    protected abstract void confirmLeadership(UUID leaderSessionID, String leaderAddress);

    /**
     * Checks whether the {@code LeaderElectionService} has the leadership acquired for the given
     * session ID.
     *
     * @return {@code true} if the service has leadership with the passed session ID acquired;
     *     {@code false} otherwise.
     */
    protected abstract boolean hasLeadership(UUID leaderSessionId);
}
