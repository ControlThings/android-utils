/**
 * Copyright (C) 2020, ControlThings Oy Ab
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * @license Apache-2.0
 */
package utils.bson;

import org.bson.BsonArray;
import org.bson.BsonWriter;

/**
 * Created by jeppe on 10/27/17.
 */

public interface BsonExtendedWriter extends BsonWriter {

    public void pipeArray(BsonArray array);

}