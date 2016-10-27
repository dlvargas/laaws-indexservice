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

package org.lockss.laaws.indexservice.api;

import org.lockss.laaws.indexservice.model.*;
import org.lockss.laaws.indexservice.api.AusApiService;
import org.lockss.laaws.indexservice.api.factories.AusApiServiceFactory;

import org.lockss.laaws.indexservice.model.Au;
import org.lockss.laaws.indexservice.model.ErrorModel;
import org.lockss.laaws.indexservice.model.Url;
import org.lockss.laaws.indexservice.model.WarcIndex;

import java.util.List;
import org.lockss.laaws.indexservice.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/aus")
@Consumes({ "application/json" })
@Produces({ "application/json" })

public class AusApi  {
   private final AusApiService delegate = AusApiServiceFactory.getAusApi();

    @GET
    @Path("/{auid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response ausAuidGet( @PathParam("auid") String auid,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ausAuidGet(auid,securityContext);
    }
    @GET
    @Path("/{auid}/urls")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response ausAuidUrlsGet( @PathParam("auid") String auid,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ausAuidUrlsGet(auid,securityContext);
    }
    @GET
    @Path("/{auid}/warcs")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response ausAuidWarcsGet( @PathParam("auid") String auid,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ausAuidWarcsGet(auid,securityContext);
    }
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response ausGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ausGet(securityContext);
    }
}
