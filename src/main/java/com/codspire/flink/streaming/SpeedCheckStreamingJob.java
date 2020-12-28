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

package com.codspire.flink.streaming;

import com.codspire.flink.streaming.model.SpeedInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SpeedCheckStreamingJob {

    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<SpeedInfo> dataStream = env.socketTextStream("localhost", 9090)
                .map(SpeedCheckStreamingJob::getSpeedEvent)
                .filter(speedInfo -> speedInfo != null)
                .filter(speedInfo -> speedInfo.getSpeed() > 50);

        dataStream.print();

        // execute program
        env.execute("SpeedCheckStreamingJob");
    }

    private static SpeedInfo getSpeedEvent(String text) {
        try {
            String[] values = text.split(",");
            return new SpeedInfo(values[0].trim(), Integer.valueOf(values[1].trim()));
        } catch (Exception e) {
            return null;
        }
    }
}
