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
import org.lockss.laaws.indexservice.api.WarcsApiService;
import org.lockss.laaws.indexservice.api.factories.WarcsApiServiceFactory;

import org.lockss.laaws.indexservice.model.WarcIndex;
import org.lockss.laaws.indexservice.model.ErrorModel;
import org.lockss.laaws.indexservice.model.Au;
import org.lockss.laaws.indexservice.model.Url;

import java.util.List;
import org.lockss.laaws.indexservice.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/warcs")
@Consumes({ "application/json" })
@Produces({ "application/json" })

public class WarcsApi  {
   private final WarcsApiService delegate = WarcsApiServiceFactory.getWarcsApi();

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response warcsGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.warcsGet(securityContext);
    }
    @GET
    @Path("/{uri}/auids")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response warcsUriAuidsGet( @PathParam("uri") String uri,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.warcsUriAuidsGet(uri,securityContext);
    }
    @DELETE
    @Path("/{uri}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response warcsUriDelete( @PathParam("uri") String uri,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.warcsUriDelete(uri,securityContext);
    }
    @GET
    @Path("/{uri}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response warcsUriGet( @PathParam("uri") String uri,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.warcsUriGet(uri,securityContext);
    }
    @GET
    @Path("/{uri}/urls")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response warcsUriUrlsGet( @PathParam("uri") String uri,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.warcsUriUrlsGet(uri,securityContext);
    }
}
