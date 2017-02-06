package com.bluepiit.etl.spouts;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.bluepiit.etl.models.KinesisInputModel;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class InputSpout extends BaseRichSpout {
    private List<String> events;
    private int nextEmitIndex;
    private SpoutOutputCollector outputCollector;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(KinesisInputModel.FIELD_RECORD_DATA));
    }

    @Override
    /**
     * This method is user for LocalTopology runner which runs Storm Locally by reading input.txt.
     */
    public void open(Map map,
                     TopologyContext topologyContext,
                     SpoutOutputCollector spoutOutputCollector) {
        this.outputCollector = spoutOutputCollector;
        this.nextEmitIndex = 0;

        try {
            events = IOUtils.readLines(ClassLoader.getSystemResourceAsStream("input.txt"),
                    Charset.defaultCharset().name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nextTuple() {
        if(nextEmitIndex <=0){
            String event = events.get(nextEmitIndex);
            outputCollector.emit(new Values(event.getBytes()));
        }
        nextEmitIndex = nextEmitIndex+1;
    }
}
