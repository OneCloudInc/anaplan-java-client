//   Copyright 2011, 2012 Anaplan Inc.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.anaplan.client;

import java.util.List;

import com.anaplan.client.serialization.TypeWrapper;

/**
 * An export object within an Anaplan model.
 */
public class Export extends TaskFactory {

    // Data passed over the wire
    static class Data extends NamedObject.Data {
    }

    static final TypeWrapper<Data> DATA_TYPE = new TypeWrapper<Data>() {
    };
    static final TypeWrapper<List<Data>> DATA_LIST_TYPE = new TypeWrapper<List<Data>>() {
    };

    Export(Model model, Data data) {
        super(model, data);
    }

    @Override
    String getPath() {
        return getModel().getPath() + "/exports/" + getId();
    }

    /**
      * Get information about the columns in the export.
      * @since 1.2
      */
    public ExportMetadata getExportMetadata() throws AnaplanAPIException {
        ExportMetadata.Data response = getSerializationHandler().deserialize(
                getTransportProvider().get(getPath(),
                        getSerializationHandler().getContentType()),
                ExportMetadata.DATA_TYPE);
        return new ExportMetadata(response);
    }
}
