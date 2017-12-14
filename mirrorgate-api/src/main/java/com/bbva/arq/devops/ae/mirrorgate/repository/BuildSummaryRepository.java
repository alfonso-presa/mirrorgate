/*
 * Copyright 2017 Banco Bilbao Vizcaya Argentaria, S.A..
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
package com.bbva.arq.devops.ae.mirrorgate.repository;

import com.bbva.arq.devops.ae.mirrorgate.model.BuildSummary;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BuildSummaryRepository extends CrudRepository<BuildSummary, ObjectId> {

    BuildSummary findByRepoNameAndProjectNameAndTimestamp(String repoName, String projectName, Long timestamp);

    @Query(value = "{$or:[{repoName: {$in: ?0 }},{projectName: {$in: ?0 }}], timestamp: {$gte: ?1}}")
    List<BuildSummary> findAllWithKeywordsAndTimestampAfter(List<String> keywords, Long timestamp);
}
