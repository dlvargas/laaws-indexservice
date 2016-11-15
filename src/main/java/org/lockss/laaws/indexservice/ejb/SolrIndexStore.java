/*
Copyright (c) 2000-2016, Board of Trustees of Leland Stanford Jr. University
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.lockss.laaws.indexservice.ejb;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import org.apache.solr.common.SolrInputDocument;
import org.jwat.warc.WarcRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;

import java.io.IOException;
import java.util.List;

@Startup
@Singleton
public class SolrIndexStore implements IndexStoreLocal {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    private static Logger log = LoggerFactory.getLogger(SolrIndexStore.class);
    private SolrClient client = null;

    @PostConstruct
    public void connect() {
        client = new HttpSolrClient.Builder("http://localhost:8983/solr/warc-records").build();
    }

    @PreDestroy
    public void close() {
        // TODO: Close Solr connection
    }

    public void addWarc(String uri) {
        log.info("Adding WARC: " + uri);

        SolrInputDocument document = new SolrInputDocument();

        executor.submit(() -> {
            // TODO: Add a WARC
            document.addField("test", "wolf");
            try {
                client.add(document);
                client.commit();

                client.deleteByQuery("test:wolf");
                client.commit();

            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void addWarcRecord(WarcRecord record) {
        log.info("Adding WARC Record: " + record);

        // TODO: Index the WARC Record's source URI, offset, AUID, and URL
        record.getPayload().getPayloadHeaderWrapped();

    }

    @Override
    public void commitWarc(String uri) {
        log.info("Committing WARC: " + uri);
        // TODO: Update committed field in all Solr documents with uri
    }

    @Override
    public void deleteWarc(String uri) {
        log.info("Removing WARC from index: " + uri);

    }

    @Override
    public List<String> urlsInAuid(String auid) {
        return null;
    }

    @Override
    public List<String> auidsInUrl(String url) {
        return null;
    }
}
